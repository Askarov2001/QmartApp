package com.example.qmartapp

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.qmartapp.databinding.ActivityMenuBinding
import com.example.qmartapp.di.appModule
import com.example.qmartapp.homePage.HomeFr
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    private val navGraphId: Int by lazy {
        R.navigation.menu_nav_graph
    }
    private val menuId: Int by lazy {
        R.menu.bottom_nav
    }

    private val defaultMenuItem: Int by lazy {
        R.id.homeScreen
    }
    private val hostFragment: Fragment? by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
    }
    private val navController: NavController by lazy {
        hostFragment?.findNavController() as NavController
    }

    private val bottomNavigation: BottomNavigationView by lazy {
        binding.bottomNavigationView
    }

    private val sp: SharedPref by lazy {
        SharedPref().apply {
            init(this@MenuActivity)
        }
    }

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MenuActivity)
            // Load modules
            modules(appModule)
        }
        navController.graph = navController.navInflater.inflate(navGraphId)
        bottomNavigation.inflateMenu(menuId)
        bottomNavigation.setupWithNavController(navController)
        bottomNavigation.setOnItemSelectedListener { item ->
            if (!navController.popBackStack(item.itemId, false)) {
                val builder = NavOptions.Builder().apply {
                    setLaunchSingleTop(true)
                    setPopUpTo(defaultMenuItem, true)
                }
                val options = builder.build()
                navController.navigate(item.itemId, null, options)
                true
            } else false
        }
        auth.addAuthStateListener {
            val uid = it.currentUser?.uid
        }
        bottomNavigation.setOnItemReselectedListener { }
    }

    fun showMessage(message: String?, length: Int = Snackbar.LENGTH_SHORT) {
        var mMessage = message
        if (message.isNullOrBlank()) {
            try {
                val snackbar = Snackbar.make(binding.root, mMessage.toString(), length)
                snackbar.show()
            } catch (e: Exception) {

            }
        }
    }

    fun addBudge(count: Int) {
        bottomNavigation.getOrCreateBadge(R.id.basketScreen).apply {
            number += count
            isVisible = number > 0
            backgroundColor = ContextCompat.getColor(this@MenuActivity, R.color.orange)
        }
    }

    fun addProperty(name: String, value: String) {
        sp.addProperty(name, value)
    }

    fun getValue(name: String): String? = sp.getProperty(name)

}
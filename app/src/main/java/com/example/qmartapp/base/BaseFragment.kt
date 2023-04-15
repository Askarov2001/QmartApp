package com.example.qmartapp.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.qmartapp.MenuActivity
import com.example.qmartapp.R
import com.google.android.material.snackbar.Snackbar

open class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    protected var baseActivity: MenuActivity? = null
    private val navHostFragment: NavHostFragment?
        get() = baseActivity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_container) as? NavHostFragment
    val navController: NavController?
        get() = navHostFragment?.navController
    open val onBackPressedOverrideCallback: (() -> Unit)? = null
    open val isRootFragment: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (activity is MenuActivity) {
            baseActivity = activity as MenuActivity
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressedOverrideCallback?.let { callback ->
            activity?.onBackPressedDispatcher?.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        callback.invoke()
                    }
                })
        }
        if (isRootFragment) {
            var showSnackBar = true
            activity?.onBackPressedDispatcher?.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        if (showSnackBar) {
                            showMessage("нажмите еще раз чтоб выйти")
                            showSnackBar = false
                        } else {
                            baseActivity?.finishAndRemoveTask()
                        }
                    }
                })
        }
    }

    override fun onDestroyView() {
        hideKeyboard()
        super.onDestroyView()
    }

    open fun hideKeyboard() {
        val inputManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputManager?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    protected open fun showMessage(message: String?, length: Int = Snackbar.LENGTH_SHORT) {
        baseActivity?.showMessage(message, length)
    }

    open fun addBudge(count: Int) = baseActivity?.addBudge(count)

    open fun addProperty(name: String, value: String) {
        baseActivity?.addProperty(name, value)
    }

    open fun getValue(name: String): String? = baseActivity?.getValue(name)

}
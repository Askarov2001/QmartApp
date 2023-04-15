package com.example.qmartapp.di

import androidx.room.Room
import com.example.qmartapp.base.database.AppDatabase
import com.example.qmartapp.basketPage.BasketRepository
import com.example.qmartapp.basketPage.BasketViewModel
import com.example.qmartapp.favouritesPage.FavouritesRepository
import com.example.qmartapp.favouritesPage.FavoutitesViewModel
import com.example.qmartapp.orderPage.OrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app.db"
        ).build()
    }
    single { get<AppDatabase>().favouriteDao() }
    single { get<AppDatabase>().basketDao() }
    single { BasketRepository(get()) }
    viewModel { BasketViewModel(get()) }
    single { FavouritesRepository(get()) }
    viewModel { FavoutitesViewModel(get()) }
    viewModel { OrderViewModel() }

}
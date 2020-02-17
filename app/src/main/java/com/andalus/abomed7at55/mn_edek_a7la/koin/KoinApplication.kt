package com.andalus.abomed7at55.mn_edek_a7la.koin

import android.app.Application
import androidx.room.Room
import com.andalus.abomed7at55.mn_edek_a7la.data.AppDatabase
import com.andalus.abomed7at55.mn_edek_a7la.data.FavoriteDao
import com.andalus.abomed7at55.mn_edek_a7la.data.RecipeDao
import com.andalus.abomed7at55.mn_edek_a7la.prefs.FavoritePrefs
import com.andalus.abomed7at55.mn_edek_a7la.prefs.PrefsManager
import com.andalus.abomed7at55.mn_edek_a7la.repositories.FavoriteRepository
import com.andalus.abomed7at55.mn_edek_a7la.repositories.FavoriteRepositoryImpl
import com.andalus.abomed7at55.mn_edek_a7la.repositories.LocalRecipesRepository
import com.andalus.abomed7at55.mn_edek_a7la.repositories.RepositoryDao
import com.andalus.abomed7at55.mn_edek_a7la.ui.details.DetailsViewModel
import com.andalus.abomed7at55.mn_edek_a7la.ui.favorite.FavoriteViewModel
import com.andalus.abomed7at55.mn_edek_a7la.ui.main.MainViewModel
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val koinModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    fun provideRecipeDao(database: AppDatabase): RecipeDao {
        return database.dao
    }

    fun provideFavoriteDao(database: AppDatabase): FavoriteDao {
        return database.favoriteDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideRecipeDao(get()) }
    single { provideFavoriteDao(get()) }
    single { FavoritePrefs(androidContext()) as PrefsManager<Int, Boolean> }
    single { LocalRecipesRepository(get(), get()) as RepositoryDao }
    single { FavoriteRepositoryImpl(get(), get(), get()) as FavoriteRepository }
    viewModel { MainViewModel(get(), androidApplication()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { FavoriteViewModel(get(), get()) }

}

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@KoinApplication)
            // declare modules
            modules(koinModule)
        }
    }
}
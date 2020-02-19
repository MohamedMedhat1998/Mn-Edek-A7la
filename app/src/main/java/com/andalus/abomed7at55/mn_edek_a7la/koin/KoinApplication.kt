package com.andalus.abomed7at55.mn_edek_a7la.koin

import android.app.Application
import androidx.room.Room
import com.andalus.abomed7at55.mn_edek_a7la.data.AppDatabase
import com.andalus.abomed7at55.mn_edek_a7la.data.FavoriteDao
import com.andalus.abomed7at55.mn_edek_a7la.data.LaterDao
import com.andalus.abomed7at55.mn_edek_a7la.data.RecipeDao
import com.andalus.abomed7at55.mn_edek_a7la.prefs.PrefsManager
import com.andalus.abomed7at55.mn_edek_a7la.prefs.PrefsManagerImpl
import com.andalus.abomed7at55.mn_edek_a7la.repositories.prefs_based.FavoriteRepository
import com.andalus.abomed7at55.mn_edek_a7la.repositories.prefs_based.LaterReposirory
import com.andalus.abomed7at55.mn_edek_a7la.repositories.prefs_based.PrefsRecipeRepository
import com.andalus.abomed7at55.mn_edek_a7la.repositories.recipe.LocalRecipeRepository
import com.andalus.abomed7at55.mn_edek_a7la.repositories.recipe.RecipeRepository
import com.andalus.abomed7at55.mn_edek_a7la.ui.category.CategoryViewModel
import com.andalus.abomed7at55.mn_edek_a7la.ui.details.DetailsViewModel
import com.andalus.abomed7at55.mn_edek_a7la.ui.favorite.FavoriteViewModel
import com.andalus.abomed7at55.mn_edek_a7la.ui.later.LaterViewModel
import com.andalus.abomed7at55.mn_edek_a7la.ui.main.MainViewModel
import com.andalus.abomed7at55.mn_edek_a7la.ui.search.SearchViewModel
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

    fun provideLaterDao(database: AppDatabase): LaterDao {
        return database.laterDao
    }

    fun provideFavoritePrefsRepo(favoriteDao: FavoriteDao, prefsManager: PrefsManager<Int, Boolean>, recipeDao: RecipeDao): PrefsRecipeRepository {
        return FavoriteRepository(favoriteDao, prefsManager, recipeDao)
    }

    fun provideLaterPrefsRepo(laterDao: LaterDao, prefsManager: PrefsManager<Int, Boolean>, recipeDao: RecipeDao): PrefsRecipeRepository {
        return LaterReposirory(laterDao, prefsManager, recipeDao)
    }

    single { provideDatabase(androidApplication()) }
    single { provideRecipeDao(get()) }
    single { provideFavoriteDao(get()) }
    single { provideLaterDao(get()) }

    single { PrefsManagerImpl(androidContext()) as PrefsManager<Int, Boolean> }
    single { LocalRecipeRepository(get(), get()) as RecipeRepository }

    viewModel { MainViewModel(get(), androidApplication()) }

    viewModel { DetailsViewModel(get(), get()) }

    viewModel { FavoriteViewModel(provideFavoritePrefsRepo(get(), get(), get()), get()) }

    viewModel { LaterViewModel(provideLaterPrefsRepo(get(), get(), get()), get()) }

    viewModel { CategoryViewModel(get()) }

    viewModel { SearchViewModel(get()) }

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
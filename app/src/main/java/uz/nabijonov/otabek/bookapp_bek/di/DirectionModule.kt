package uz.nabijonov.otabek.bookapp_bek.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.about.AboutBookDirection
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.about.AboutBookDirectionImpl
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.bookread.BookReadDirection
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.bookread.BookReadDirectionImpl
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.home.HomeDirection
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.home.HomeDirectionImpl
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.search.SearchDirection
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.search.SearchDirectionImpl
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.savedbooks.SavedBookDirection
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.savedbooks.SavedBookDirectionImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun bindHomeScreenDirection(impl: SearchDirectionImpl): SearchDirection

    @Binds
    fun bindSavedBooksScreenDirection(impl: SavedBookDirectionImpl): SavedBookDirection

    @Binds
    fun bindExploreScreenDirection(impl: HomeDirectionImpl): HomeDirection

    @Binds
    fun bindBookReadScreenDirection(impl: BookReadDirectionImpl): BookReadDirection

    @Binds
    fun bindAboutBookScreenDirection(impl: AboutBookDirectionImpl): AboutBookDirection
}
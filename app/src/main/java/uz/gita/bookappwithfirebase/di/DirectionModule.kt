package uz.gita.bookappwithfirebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.bookappwithfirebase.presentation.ui.screens.about.AboutBookDirection
import uz.gita.bookappwithfirebase.presentation.ui.screens.about.AboutBookDirectionImpl
import uz.gita.bookappwithfirebase.presentation.ui.screens.bookread.BookReadDirection
import uz.gita.bookappwithfirebase.presentation.ui.screens.bookread.BookReadDirectionImpl
import uz.gita.bookappwithfirebase.presentation.ui.screens.explore.ExploreScreenDirection
import uz.gita.bookappwithfirebase.presentation.ui.screens.explore.ExploreScreenDirectionImpl
import uz.gita.bookappwithfirebase.presentation.ui.screens.home.HomeDirection
import uz.gita.bookappwithfirebase.presentation.ui.screens.home.HomeDirectionImpl
import uz.gita.bookappwithfirebase.presentation.ui.screens.savedbooks.SavedBookDirection
import uz.gita.bookappwithfirebase.presentation.ui.screens.savedbooks.SavedBookDirectionImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun bindHomeScreenDirection(impl: HomeDirectionImpl): HomeDirection

    @Binds
    fun bindSavedBooksScreenDirection(impl: SavedBookDirectionImpl): SavedBookDirection

    @Binds
    fun bindExploreScreenDirection(impl: ExploreScreenDirectionImpl): ExploreScreenDirection

    @Binds
    fun bindBookReadScreenDirection(impl: BookReadDirectionImpl): BookReadDirection

    @Binds
    fun bindAboutBookScreenDirection(impl: AboutBookDirectionImpl): AboutBookDirection
}
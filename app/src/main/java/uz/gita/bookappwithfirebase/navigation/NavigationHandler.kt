package uz.gita.bookappwithfirebase.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationHandler {
    val navigationBuffer: Flow<NavigationArg>
}
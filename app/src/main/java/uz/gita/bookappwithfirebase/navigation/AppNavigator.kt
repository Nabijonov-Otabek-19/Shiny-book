package uz.gita.bookappwithfirebase.navigation


interface AppNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()
}
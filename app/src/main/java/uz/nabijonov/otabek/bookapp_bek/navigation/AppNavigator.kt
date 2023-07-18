package uz.nabijonov.otabek.bookapp_bek.navigation


interface AppNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()
}
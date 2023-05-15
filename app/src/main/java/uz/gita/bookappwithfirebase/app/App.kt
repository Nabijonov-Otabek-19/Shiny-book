package uz.gita.bookappwithfirebase.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.gita.bookappwithfirebase.data.source.local.SharedPref

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}
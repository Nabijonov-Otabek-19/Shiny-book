package uz.nabijonov.otabek.bookapp_bek.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.nabijonov.otabek.bookapp_bek.data.source.local.SharedPref

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}
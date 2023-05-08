package uz.gita.bookappwithfirebase.app

import android.app.Application
import uz.gita.bookappwithfirebase.data.source.local.SharedPref

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}
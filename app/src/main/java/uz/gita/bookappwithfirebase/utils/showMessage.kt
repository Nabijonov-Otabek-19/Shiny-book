package uz.gita.bookappwithfirebase.utils

import android.content.Context
import android.util.Log
import android.widget.Toast


fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Any.logd(message: String) {
    Log.d("AAA", message)
}
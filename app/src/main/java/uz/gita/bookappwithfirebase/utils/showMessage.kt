package uz.gita.bookappwithfirebase.utils

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toasT(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun logd(message: String) {
    Log.d("AAA", message)
}
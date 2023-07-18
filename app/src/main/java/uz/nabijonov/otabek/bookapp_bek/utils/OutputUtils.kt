package uz.nabijonov.otabek.bookapp_bek.utils

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toasT(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun logger(message: String) {
    Log.d("AAA", message)
}
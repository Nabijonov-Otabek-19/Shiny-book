package uz.gita.bookappwithfirebase.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.databinding.ScreenHomeBinding
import uz.gita.bookappwithfirebase.presentation.ui.adapters.HomeAdapter

class HomeScreen : Fragment(R.layout.screen_home) {

    private val viewBinding by viewBinding(ScreenHomeBinding::bind)
    private val adapter by lazy { HomeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = ArrayList<BookData>()
        (0..15).forEach {
            list.add(BookData(it, "Good to great sfsdfjsfj", R.drawable.icon, ""))
        }

        adapter.setData(list)

        viewBinding.apply {
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
            recycler.adapter = adapter
        }
    }
}
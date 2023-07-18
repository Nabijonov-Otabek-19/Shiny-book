package uz.nabijonov.otabek.bookapp_bek.presentation.ui.screens.savedbooks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.nabijonov.otabek.bookapp_bek.R
import uz.nabijonov.otabek.bookapp_bek.data.source.local.SharedPref
import uz.nabijonov.otabek.bookapp_bek.databinding.ScreenSavedBinding
import uz.nabijonov.otabek.bookapp_bek.presentation.ui.adapters.SavedAdapter
import uz.nabijonov.otabek.bookapp_bek.utils.logger
import uz.nabijonov.otabek.bookapp_bek.utils.toasT
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class SavedBooksScreen : Fragment(R.layout.screen_saved) {

    private val binding by viewBinding(ScreenSavedBinding::bind)
    private val viewModel by viewModels<SavedViewModelImpl>()

    @Inject
    lateinit var adapter: SavedAdapter

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAllData(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (sharedPref.bookName.isEmpty()) {
            binding.view.visibility = View.GONE
        } else {
            binding.apply {
                binding.view.visibility = View.VISIBLE
                txtBookName.text = sharedPref.bookName
                percentageView.text = "${sharedPref.percentage}%"
            }
        }

        adapter.setClickListener {
            viewModel.navigateToReadBookScreen(it.name, 0, it.page.toInt())
        }

        adapter.setDeleteClickListener {
            val file = File(requireContext().filesDir, it.name)
            val deleted = if (file.exists()) file.delete() else false

            if (deleted){
                viewModel.getAllData(requireContext())
                toasT("Book deleted")
            }
            else {
                toasT("File not found")
            }

            if (sharedPref.bookName == it.name) sharedPref.deleteCurrentBook()
        }

        binding.apply {
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter

            // Last Read book
            btnLastBook.setOnClickListener {
                viewModel.navigateToReadBookScreen(
                    sharedPref.bookName,
                    sharedPref.savedPage,
                    sharedPref.totalPage
                )
            }
        }

        viewModel.booksData.observe(viewLifecycleOwner) {
            logger("SavedScreen Data = ${it.size}")
            if (it.isEmpty()) {
                binding.apply {
                    imgNoBooks.visibility = View.VISIBLE
                    txtNoBookTitle.visibility = View.VISIBLE
                }
            } else {
                binding.apply {
                    imgNoBooks.visibility = View.GONE
                    txtNoBookTitle.visibility = View.GONE
                }
            }
            adapter.setData(it)
        }

        viewModel.errorData.observe(viewLifecycleOwner) {
            toasT(it)
            logger("SavedScreen error = $it")
        }
    }
}
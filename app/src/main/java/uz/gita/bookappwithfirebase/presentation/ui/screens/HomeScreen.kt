package uz.gita.bookappwithfirebase.presentation.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.databinding.ScreenHomeBinding
import uz.gita.bookappwithfirebase.presentation.ui.adapters.HomeAdapter
import uz.gita.bookappwithfirebase.presentation.viewmodels.impl.HomeViewModelImpl
import uz.gita.bookappwithfirebase.repository.impl.AppRepositoryImpl
import uz.gita.bookappwithfirebase.utils.logd

class HomeScreen : Fragment(R.layout.screen_home) {

    private val viewBinding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModelImpl>()
    private val adapter by lazy { HomeAdapter() }

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val repository = AppRepositoryImpl()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.bookFilesData.observe(viewLifecycleOwner) {
//            logd(it.toString())
//        }

        viewBinding.btnLoadFiles.setOnClickListener {
            repository.downloadFile()
                .onEach { list ->
                    list.onSuccess { logd(it.name) }
                    list.onFailure { logd(it.toString()) }
                }.launchIn(scope)
        }

        adapter.setClickListener {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToBookReadScreen(it))
        }

        viewModel.booksData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.errorData.observe(viewLifecycleOwner) {
            logd("Errors = $it")

            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.categoriesData.observe(viewLifecycleOwner) {

        }

        viewBinding.apply {
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
            recycler.adapter = adapter
        }
    }
}
package uz.nabijonov.otabek.bookapp_bek.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.nabijonov.otabek.bookapp_bek.data.common.AllBooksData
import uz.nabijonov.otabek.bookapp_bek.data.common.BookData
import uz.nabijonov.otabek.bookapp_bek.databinding.VerticalItemBinding
import javax.inject.Inject

class HomeAdapter @Inject constructor() : Adapter<HomeAdapter.ItemHolder>() {

    private var list: List<AllBooksData> = ArrayList()

    fun setData(l: List<AllBooksData>) {
        list = l
        notifyDataSetChanged()
    }

    private var clickListener: ((BookData) -> Unit)? = null

    fun setClickListener(l: (BookData) -> Unit) {
        clickListener = l
    }

    inner class ItemHolder(private val binding: VerticalItemBinding) :
        ViewHolder(binding.root) {

        fun bind(data: AllBooksData) = with(binding) {
            val innerAdapter = HorizontalHomeAdapter()
            innerAdapter.setData(data.books)
            horizontalRv.adapter = innerAdapter
            horizontalRv.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)

            binding.category.text = data.categoryName

            innerAdapter.setClickListener {
                clickListener?.invoke(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            VerticalItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(list[position])
    }
}
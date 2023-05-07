package uz.gita.bookappwithfirebase.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.bookappwithfirebase.data.common.AllBooksData
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.databinding.VerticalItemBinding

class ExploreAdapter : Adapter<ExploreAdapter.ItemHolder>() {

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

        private val innerAdapter = HorizontalExploreAdapter()

        fun bind() {
            list[bindingAdapterPosition].apply {
                innerAdapter.setData(this.books)
                binding.horizontalRv.adapter = innerAdapter
                binding.horizontalRv.layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)

                binding.category.text = this.categoryName

                innerAdapter.setClickListener {
                    clickListener?.invoke(it)
                }
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
        holder.bind()
    }
}
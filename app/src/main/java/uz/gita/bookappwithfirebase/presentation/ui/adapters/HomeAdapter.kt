package uz.gita.bookappwithfirebase.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.databinding.ItemBookBinding

class HomeAdapter : Adapter<HomeAdapter.ItemHolder>() {

    private lateinit var list: ArrayList<BookData>

    fun setData(l: ArrayList<BookData>) {
        list = l
    }

    inner class ItemHolder(private val binding: ItemBookBinding) :
        ViewHolder(binding.root) {

        fun bind() {
            binding.apply {
                imgIcon.setImageResource(list[adapterPosition].icon)
                txtTitle.text = list[adapterPosition].name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemBookBinding.inflate(
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
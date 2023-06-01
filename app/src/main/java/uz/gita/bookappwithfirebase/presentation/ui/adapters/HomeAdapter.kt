package uz.gita.bookappwithfirebase.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.databinding.ItemRecommendBookBinding
import javax.inject.Inject

class HomeAdapter @Inject constructor() : Adapter<HomeAdapter.ItemHolder>() {

    private var list: List<BookData> = ArrayList()

    fun setData(l: List<BookData>) {
        list = l
        notifyItemRangeChanged(0, list.size)
    }

    private var clickListener: ((BookData) -> Unit)? = null

    fun setClickListener(l: (BookData) -> Unit) {
        clickListener = l
    }

    inner class ItemHolder(private val binding: ItemRecommendBookBinding) :
        ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clickListener?.invoke(list[adapterPosition])
            }
        }

        fun bind() {
            binding.apply {
                txtTitle.text = list[adapterPosition].name
                txtAuthor.text = list[adapterPosition].author

                val imgUrl = list[adapterPosition].bookCoverUrl

                Glide.with(binding.root.context).load(imgUrl)
                    .into(imgIcon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemRecommendBookBinding.inflate(
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
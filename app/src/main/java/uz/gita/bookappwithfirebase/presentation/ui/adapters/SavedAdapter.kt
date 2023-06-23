package uz.gita.bookappwithfirebase.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.databinding.ItemSavedBookBinding
import javax.inject.Inject

class SavedAdapter @Inject constructor() : Adapter<SavedAdapter.ItemHolder>() {

    private var list: List<BookData> = ArrayList()

    fun setData(l: List<BookData>) {
        list = l
        notifyDataSetChanged()
    }

    private var clickListener: ((BookData) -> Unit)? = null
    private var deleteClickListener: ((BookData) -> Unit)? = null

    fun setClickListener(l: (BookData) -> Unit) {
        clickListener = l
    }

    fun setDeleteClickListener(l: (BookData) -> Unit) {
        deleteClickListener = l
    }

    inner class ItemHolder(private val binding: ItemSavedBookBinding) :
        ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clickListener?.invoke(list[adapterPosition])
            }

            binding.btnDelete.setOnClickListener {
                deleteClickListener?.invoke(list[adapterPosition])
            }
        }

        fun bind() {
            binding.apply {
                txtTitle.text = list[adapterPosition].name
                txtAuthor.text = list[adapterPosition].author

                val imgUrl = list[adapterPosition].bookCoverUrl

                if (imgUrl.isEmpty()) {
                    imgIcon.setImageResource(R.drawable.icon_book)
                } else {
                    Glide.with(binding.root.context).load(list[adapterPosition].bookCoverUrl)
                        .into(imgIcon)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemSavedBookBinding.inflate(
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
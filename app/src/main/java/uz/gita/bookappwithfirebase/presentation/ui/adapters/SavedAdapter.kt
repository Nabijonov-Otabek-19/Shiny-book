package uz.gita.bookappwithfirebase.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.bookappwithfirebase.data.common.BookData
import uz.gita.bookappwithfirebase.databinding.ItemSavedBookBinding

class SavedAdapter : Adapter<SavedAdapter.ItemHolder>() {

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
                clickListener?.invoke(list[bindingAdapterPosition])
            }

            binding.btnDelete.setOnClickListener {
                deleteClickListener?.invoke(list[bindingAdapterPosition])
            }
        }

        fun bind() {
            binding.apply {
                txtTitle.text = list[bindingAdapterPosition].name
                Glide.with(binding.root.context).load(list[bindingAdapterPosition].bookCoverUrl)
                    .into(imgIcon)
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
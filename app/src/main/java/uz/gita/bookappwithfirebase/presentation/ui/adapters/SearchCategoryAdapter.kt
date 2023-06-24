package uz.gita.bookappwithfirebase.presentation.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.bookappwithfirebase.R
import uz.gita.bookappwithfirebase.data.common.CategoryData
import uz.gita.bookappwithfirebase.databinding.ItemCategoryBinding
import javax.inject.Inject

class SearchCategoryAdapter @Inject constructor() : Adapter<SearchCategoryAdapter.ItemHolder>() {

    private var categoryList: List<CategoryData> = ArrayList()

    private var clickListener: ((String) -> Unit)? = null

    fun setClickListener(l: (String) -> Unit) {
        clickListener = l
    }

    fun setData(l: List<CategoryData>) {
        categoryList = l
        notifyDataSetChanged()
    }

    inner class ItemHolder(private val binding: ItemCategoryBinding) :
        ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {

                categoryList.forEach { d ->
                    d.checked = false
                }

                categoryList[adapterPosition].checked = true

                notifyItemRangeChanged(0, categoryList.size)
                clickListener?.invoke((categoryList[adapterPosition]).title)
            }
        }

        fun bind() {
            binding.txtCategory.text = categoryList[adapterPosition].title

            if (categoryList[adapterPosition].checked) {
                binding.root.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.category_bg)

                binding.txtCategory.setTextColor(Color.WHITE)
            } else {
                binding.root.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.edittext_bg)

                binding.txtCategory.setTextColor(Color.BLACK)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind()
    }
}
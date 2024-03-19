package com.rendi.foodorderapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rendi.foodorderapp.base.ViewHolderBinder
import com.rendi.foodorderapp.databinding.ItemCategoryBinding
import com.rendi.foodorderapp.model.Category

class CategoryAdapter() : RecyclerView.Adapter<ViewHolder>() {

    private var asyncDataDiffer = AsyncListDiffer(
        this, object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )

    fun submitData(data: List<Category>) {
        asyncDataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = asyncDataDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder !is ViewHolderBinder<*>) return
        (holder as ViewHolderBinder<Category>).bind(asyncDataDiffer.currentList[position])
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        ViewHolder(binding.root), ViewHolderBinder<Category> {
        override fun bind(item: Category) {
            item.let {
                binding.tvCategoryName.text = it.name
                binding.ivCategoryImage.setImageResource(it.image)
            }
        }
    }
}
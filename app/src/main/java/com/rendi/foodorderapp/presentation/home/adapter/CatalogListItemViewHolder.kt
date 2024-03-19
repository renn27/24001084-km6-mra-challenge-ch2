package com.rendi.foodorderapp.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.rendi.foodorderapp.R
import com.rendi.foodorderapp.base.ViewHolderBinder
import com.rendi.foodorderapp.databinding.ItemCatalogListBinding
import com.rendi.foodorderapp.model.Catalog
import com.rendi.foodorderapp.utils.toIndonesianFormat

class CatalogListItemViewHolder(
    private val binding: ItemCatalogListBinding,
    private val listener: OnItemClickedListener<Catalog>
) : ViewHolder(binding.root), ViewHolderBinder<Catalog> {
    override fun bind(item: Catalog) {
        item.let {
            binding.ivCatalogImage.load(it.imagePictUrl) {
                crossfade(true)
                error(R.drawable.img_ayam_bakar)
            }
            binding.tvCatalogName.text = it.name
            binding.tvCatalogPrice.text = it.price.toIndonesianFormat()
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }
}
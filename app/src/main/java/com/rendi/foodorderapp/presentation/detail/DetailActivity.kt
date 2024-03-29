package com.rendi.foodorderapp.presentation.detail

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.rendi.foodorderapp.R
import com.rendi.foodorderapp.databinding.ActivityDetailBinding
import com.rendi.foodorderapp.model.Catalog
import com.rendi.foodorderapp.utils.toIndonesianFormat

class DetailActivity : AppCompatActivity() {

    private var itemQuantity: Int = 1
    private var itemPrice: Double = 0.0
    private var urlLocation: String = "https://maps.app.goo.gl/mQHCn8ZiDZGpjk3AA"

    companion object {
        const val EXTRAS_DETAIL_DATA = "EXTRA_DETAIL_DATA"
        fun startActivity(context: Context, catalog: Catalog) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRAS_DETAIL_DATA, catalog)
            context.startActivity(intent)
        }
    }

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getIntentData()
        setClickAction()
    }

    private fun getIntentData() {
        intent.extras?.getParcelable<Catalog>(EXTRAS_DETAIL_DATA)?.let {
            setData(it)
        }
    }

    private fun setData(it: Catalog) {
        binding.ivCatalogImage.load(it.imagePictUrl) {
            crossfade(true)
            error(R.drawable.img_ayam_bakar)
        }
        binding.tvCatalogName.text = it.name
        binding.tvCatalogPrice.text = it.price.toIndonesianFormat()
        binding.tvDesc.text = it.description
        binding.tvLocation.text = it.location
        itemPrice = it.price
        urlLocation = it.urlLocation
        bindCountPrice()
    }

    private fun setClickAction() {
        binding.tvLocation.setOnClickListener {
            openMap()
        }
        binding.ibSubItemCount.setOnClickListener {
            subItemCount()
        }
        binding.ibAddItemCount.setOnClickListener {
            addItemCount()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun addItemCount() {
        itemQuantity += 1
        bindCountPrice()
    }

    private fun subItemCount() {
        if (itemQuantity > 1) {
            itemQuantity -= 1
            bindCountPrice()
        } else {
            Toast.makeText(this, "Minimal Pembelian Adalah 1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindCountPrice() {
        binding.btnAddToCart.text = getString(R.string.add_to_cart, getTotalPrice(itemQuantity, itemPrice))
        binding.tvAddCountCart.text = itemQuantity.toString()
    }

    private fun openMap() {
        val gmmIntentUri = Uri.parse(urlLocation)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        try {
            startActivity(mapIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "No maps found, please install maps apps", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getTotalPrice(quantity: Int, itemPrice: Double): String? {
        val totalPrice = quantity * itemPrice
        return totalPrice.toIndonesianFormat()
    }
}
package com.rendi.foodorderapp.data.datasource

import com.rendi.foodorderapp.R
import com.rendi.foodorderapp.model.Category

interface CategoryDataSource {
    fun getCategoryItem(): List<Category>
}

class CategoryDataSourceImpl : CategoryDataSource {
    override fun getCategoryItem(): List<Category> {
        return mutableListOf(
            Category(
                image = R.drawable.ic_rice, name = "Nasi"
            ), Category(
                image = R.drawable.ic_noodle, name = "Mie"
            ), Category(
                image = R.drawable.ic_snack, name = "Snack"
            ), Category(
                image = R.drawable.ic_drink, name = "Minuman"
            ), Category(
                image = R.drawable.ic_cake, name = "Kue"
            )
        )
    }
}

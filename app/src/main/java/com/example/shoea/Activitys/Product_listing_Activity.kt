package com.example.shoea.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoea.R

class Product_listing_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_listing)
        supportActionBar?.hide()
    }
}
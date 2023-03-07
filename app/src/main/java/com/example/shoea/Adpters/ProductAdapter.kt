package com.example.shoea.Adpters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoea.Models.ProductData
import com.example.shoea.R

class ProductAdapter(private val context: Context, private val products: List<ProductData>)
    : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)
        val stockTextView: TextView = itemView.findViewById(R.id.stockTextView)
        val brandTextView: TextView = itemView.findViewById(R.id.brandTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        Glide.with(holder.itemView.context).load(product.images).into(holder.imageView)
        holder.titleTextView.text = product.title
        holder.titleTextView.text = products[position].title
        holder.categoryTextView.text = product.title
        holder.stockTextView.text =product.stock.toString()
        holder.brandTextView.text = product.brand
        holder.priceTextView.text = product.stock.toString()
    }

    override fun getItemCount() = products.size

}

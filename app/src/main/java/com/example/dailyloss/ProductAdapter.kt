package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter (
    private var imageList: List<Bitmap>,
    private var textList: List<ProductData>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_product_image)
        val textView1: TextView = itemView.findViewById(R.id.item_product_title)
        val textView2: TextView = itemView.findViewById(R.id.item_product_grams)
        val textView3: TextView = itemView.findViewById(R.id.item_product_calorieValue)
        val button: Button = itemView.findViewById(R.id.item_product_Button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageResId = imageList[position]
        holder.imageView.setImageBitmap(imageResId)

        val itemData = textList[position]
        holder.textView1.text = itemData.text1
        holder.textView2.text = itemData.text2
        holder.textView3.text = itemData.text3

        holder.button.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, Search_Product_Check::class.java)

            intent.putExtra("text1", itemData.text1)
            intent.putExtra("text2", itemData.text2)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        // return imageList.size.coerceAtMost(textList.size)
        return minOf(textList.size, imageList.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItemList: List<ProductData>, newBitmapList: List<Bitmap>) {
        textList = newItemList
        imageList = newBitmapList
        notifyDataSetChanged()
    }
}
package com.example.dailyloss

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.content.SharedPreferences

class MealAdapter(
    private val context: Context,
    private val items: MutableList<List<String>>,
    private val colors: List<Int>,
    private val sharedPreferences: SharedPreferences
) : RecyclerView.Adapter<MealAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView1: TextView = view.findViewById(R.id.item_meal_title)
        val textView2: TextView = view.findViewById(R.id.item_meal_grams)
        val textView3: TextView = view.findViewById(R.id.item_meal_caloriesValue)
        val textView4: TextView = view.findViewById(R.id.item_meal_proteinValue)
        val textView5: TextView = view.findViewById(R.id.item_meal_carbsValue)
        val textView6: TextView = view.findViewById(R.id.item_meal_fatsValue)
        val buttonDelete: ImageButton = view.findViewById(R.id.item_meal_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_meal, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.textView1.text = item[0]
        holder.textView2.text = item[1]
        holder.textView3.text = item[2]
        holder.textView4.text = item[3]
        holder.textView5.text = item[4]
        holder.textView6.text = item[5]

        holder.textView3.setTextColor(ContextCompat.getColor(context, colors[0]))
        holder.textView4.setTextColor(ContextCompat.getColor(context, colors[0]))
        holder.textView5.setTextColor(ContextCompat.getColor(context, colors[0]))
        holder.textView6.setTextColor(ContextCompat.getColor(context, colors[0]))

        holder.buttonDelete.setOnClickListener {
            val editor = sharedPreferences.edit()
            val codeList = SharedPreferenceHelper2.getList(context).toMutableList()
            val key = item[6]

            val iterator = codeList.iterator()
            while (iterator.hasNext()) {
                val element = iterator.next()
                if (element == key) {
                    iterator.remove()
                }
            }

            editor.remove("SelectedProductsList")
            var string = key + "_" + "Name"
            editor.remove(string)
            string = key + "_" + "Amount"
            editor.remove(string)
            string = key + "_" + "Calories"
            editor.remove(string)
            string = key + "_" + "Protein"
            editor.remove(string)
            string = key + "_" + "Carbs"
            editor.remove(string)
            string = key + "_" + "Fats"
            editor.remove(string)

            editor.apply()
            SharedPreferenceHelper2.saveList(context, codeList)

            removeItem(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(newItem: List<String>) {
        items.add(newItem)
        notifyItemInserted(items.size - 1)
    }

    private fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }
}
package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class LanguageAdapter(private var context: Context, private var arrayList: ArrayList<LanguageItem>): BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return arrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.main_page_grid, null)
        val icons: ImageView = view.findViewById(R.id.mainPage_card_image)
        val languageItem: LanguageItem = arrayList[p0]
        icons.setImageResource(languageItem.icons!!)

        return view
    }
}
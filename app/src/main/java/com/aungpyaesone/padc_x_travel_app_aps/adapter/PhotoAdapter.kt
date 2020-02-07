package com.aungpyaesone.padc_x_travel_app_aps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aungpyaesone.padc_x_travel_app_aps.R
import com.aungpyaesone.padc_x_travel_app_aps.view.viewholder.BaseViewHolder
import com.aungpyaesone.padc_x_travel_app_aps.view.viewholder.PhotoViewHolder

class PhotoAdapter:BaseAdapter<BaseViewHolder<String>,String>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item_view,parent,false)
        return PhotoViewHolder(view)
    }
}
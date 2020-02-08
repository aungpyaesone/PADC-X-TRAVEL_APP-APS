package com.aungpyaesone.padc_x_travel_app_aps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aungpyaesone.padc_x_travel_app_aps.R
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.DataVO
import com.aungpyaesone.padc_x_travel_app_aps.delegation.CountryItemDelegate
import com.aungpyaesone.padc_x_travel_app_aps.view.viewholder.BaseViewHolder
import com.aungpyaesone.padc_x_travel_app_aps.view.viewholder.MainViewHolder

class MainAdapter(delegate: CountryItemDelegate): BaseAdapter<BaseViewHolder<DataVO>,DataVO>(){

    val mDelegate = delegate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DataVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item_view,parent,false)
        return MainViewHolder(view,mDelegate)
    }

}
package com.aungpyaesone.padc_x_travel_app_aps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aungpyaesone.padc_x_travel_app_aps.R
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.delegation.CountryItemDelegate
import com.aungpyaesone.padc_x_travel_app_aps.view.viewholder.BaseViewHolder
import com.aungpyaesone.padc_x_travel_app_aps.view.viewholder.PopularTourViewHolder

class PopularTourListAdapter(delegate: CountryItemDelegate) : BaseAdapter<BaseViewHolder<CountryVO>, CountryVO>() {
    val mDelegate = delegate
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CountryVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vertical_item_view, parent,false)
        return PopularTourViewHolder(view,mDelegate)
    }
}
package com.aungpyaesone.padc_x_travel_app_aps.view.viewholder

import android.util.Log
import android.view.View
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.delegation.CountryItemDelegate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.horizontal_item_view.view.*

class CountryViewHolder(itemView: View,delegate:CountryItemDelegate) : BaseViewHolder<CountryVO>(itemView) {

    init {
        itemView.setOnClickListener {
            mData?.let {
                countriesdata->
                delegate.onTouchCountryItem(countriesdata.name)
            }

        }
    }

    override fun bindData(data: CountryVO) {
        mData = data

        Glide.with(itemView.context)
            .load(data.photos[1])
            .centerCrop()
            .into(itemView.imgTravel)

       // itemView.tvLabelOne.text = data.location
        val str:String = getCountryName(data.location)
        itemView.tvLocation.text = str
        itemView.tvRating.text = data.average_rating.toString()
        Log.d("location",data.location)


    }

}
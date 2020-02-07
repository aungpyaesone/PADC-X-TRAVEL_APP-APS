package com.aungpyaesone.padc_x_travel_app_aps.view.viewholder

import android.util.Log
import android.view.View
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.delegation.CountryItemDelegate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.vertical_item_view.view.*

class PopularTourViewHolder(itemView: View,delegate: CountryItemDelegate) : BaseViewHolder<CountryVO>(itemView) {

    init {
        itemView.setOnClickListener {
            mData?.let {countrydata->
                delegate.onTouchCountryItem(countrydata.name)
            }

        }
    }


    override fun bindData(data: CountryVO) {
        mData = data
        Glide.with(itemView.context)
            .load(data.photos[1])
            .centerCrop()
            .into(itemView.ImgOne)

        val title:String = getCountryName(data.location)
        itemView.title.text = title
        itemView.tvRate.text = data.average_rating.toString()
        Log.d("title",title)

    }
}
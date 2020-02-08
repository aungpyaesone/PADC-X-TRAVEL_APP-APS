package com.aungpyaesone.padc_x_travel_app_aps.view.viewholder

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aungpyaesone.padc_x_travel_app_aps.adapter.CountryListAdapter
import com.aungpyaesone.padc_x_travel_app_aps.adapter.PopularTourListAdapter
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.DataVO
import com.aungpyaesone.padc_x_travel_app_aps.delegation.CountryItemDelegate
import com.aungpyaesone.padc_x_travel_app_aps.utils.EN_CONNECTION_ERROR
import com.aungpyaesone.padc_x_travel_app_aps.view.viewpods.EmptyViewPod
import kotlinx.android.synthetic.main.main_item_view.*
import kotlinx.android.synthetic.main.main_item_view.view.*

class MainViewHolder(itemView: View,delegate: CountryItemDelegate) : BaseViewHolder<DataVO>(itemView) {

    val mDelegate = delegate
    private lateinit var  viewPortEmpty: EmptyViewPod

    private lateinit var mCountryListAdapter:CountryListAdapter
    private lateinit var mPopularTourListAdapter: PopularTourListAdapter
    override fun bindData(data: DataVO) {
        hideEmptyView()
        mData = data
        setUpRecycler()
        if(data.country.isNotEmpty()){
            mCountryListAdapter.setData(data.country.toMutableList())
            mPopularTourListAdapter.setData(data.popular_tour.toMutableList())
        }

    }

    private fun setUpRecycler(){
        mCountryListAdapter = CountryListAdapter(mDelegate)
        mPopularTourListAdapter = PopularTourListAdapter(mDelegate)
        val mManagerOne = LinearLayoutManager(itemView.context,LinearLayoutManager.HORIZONTAL,false)
        val mManagerTwo = LinearLayoutManager(itemView.context,LinearLayoutManager.VERTICAL,false)

        itemView.horizontalRecycler.layoutManager = mManagerOne
        itemView.verticalRecyclerView.layoutManager = mManagerTwo

        itemView.horizontalRecycler.adapter = mCountryListAdapter
        itemView.verticalRecyclerView.adapter = mPopularTourListAdapter
/*itemView.horizontalRecycler.setRecycledViewPool(viewPool)
        itemView.verticalRecyclerView.setRecycledViewPool(viewPool)*/

    }

    private fun setUpViewPod(){
        viewPortEmpty = itemView.vpEmpty as EmptyViewPod
     //   viewPortEmpty = itemView.vpEmptyTwo as EmptyViewPod
        viewPortEmpty.setEmptyData(EN_CONNECTION_ERROR,"https://i.pinimg.com/originals/ae/8a/c2/ae8ac2fa217d23aadcc913989fcc34a2.png")

    }

    private fun showEmptyView(){
        itemView.vpEmpty.visibility = View.VISIBLE
     //   itemView.vpEmptyTwo.visibility = View.VISIBLE

    }

    private fun hideEmptyView(){
        itemView.vpEmpty.visibility = View.GONE
      //  itemView.vpEmptyTwo.visibility = View.GONE
    }

}
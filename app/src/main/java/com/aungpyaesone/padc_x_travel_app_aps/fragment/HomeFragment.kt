package com.aungpyaesone.padc_x_travel_app_aps.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.aungpyaesone.padc_x_travel_app_aps.R
import com.aungpyaesone.padc_x_travel_app_aps.adapter.CountryListAdapter
import com.aungpyaesone.padc_x_travel_app_aps.adapter.PopularTourListAdapter
import com.aungpyaesone.padc_x_travel_app_aps.data.models.TourModel
import com.aungpyaesone.padc_x_travel_app_aps.data.models.TourModelImpl
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.delegation.CountryItemDelegate
import com.aungpyaesone.padc_x_travel_app_aps.utils.ACCESS_TOKEN
import com.aungpyaesone.padc_x_travel_app_aps.utils.EN_CONNECTION_ERROR
import com.aungpyaesone.padc_x_travel_app_aps.view.viewpods.EmptyViewPod
import kotlinx.android.synthetic.main.main_item_view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : BaseFragment(),CountryItemDelegate{

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mAdatper:CountryListAdapter
    private lateinit var mPopularAdapter: PopularTourListAdapter


    val mTourImpl: TourModel = TourModelImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val v = inflater.inflate(R.layout.fragment_home, container, false)
        return v
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        setUpViewPod()

    }

    override fun onResume() {
        super.onResume()
        setUpSwipeRefresh()
        requestData()
        hideEmptyView()
    }

    private fun setUpSwipeRefresh(){
        swipeRefreshLayout.setOnRefreshListener {
            requestData()
        }
    }
    private fun setUpRecyclerView(){
        mAdatper = CountryListAdapter(this)
        mPopularAdapter = PopularTourListAdapter(this)
        val linearLayoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        val linearLayoutManagerTwo = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)


        horizontalRecycler.layoutManager = linearLayoutManagerTwo
        verticalRecyclerView.layoutManager = linearLayoutManager
        horizontalRecycler.adapter = mAdatper
        verticalRecyclerView.adapter = mPopularAdapter

    }

    private fun requestData(){
        swipeRefreshLayout.isRefreshing = true
        mTourImpl.getAllTours(
            onSuccess = {countryList,popularTourList ->
                if(countryList.isNotEmpty() || popularTourList.isNotEmpty()){
                   mAdatper.setData(countryList.toMutableList())
                   mPopularAdapter.setData(popularTourList.toMutableList())

                }
                else
                {
                    showEmptyView()
                }
                swipeRefreshLayout.isRefreshing = false
            },
            onFailure = {
               showEmptyView()
               swipeRefreshLayout.isRefreshing = false
            }
        )


    }

    private fun setUpViewPod(){
        viewPortEmpty = vpEmpty as EmptyViewPod
       // viewPortEmpty = vpEmptyTwo as EmptyViewPod
        viewPortEmpty.setEmptyData(EN_CONNECTION_ERROR,"https://i.pinimg.com/originals/ae/8a/c2/ae8ac2fa217d23aadcc913989fcc34a2.png")

    }

    private fun showEmptyView(){
        vpEmpty.visibility = View.VISIBLE
        vpEmptyTwo.visibility = View.VISIBLE

    }

    private fun hideEmptyView(){
        vpEmpty.visibility = View.GONE
        vpEmptyTwo.visibility = View.GONE
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onTouchCountryItem(name:String) {
       // showSnackbar(name)
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container,DetailFragment.newInstance(name))?.addToBackStack(null)
            ?.commit()
    }
}

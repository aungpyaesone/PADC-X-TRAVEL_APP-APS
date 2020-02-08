package com.aungpyaesone.padc_x_travel_app_aps.data.models

import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.DataVO
import com.aungpyaesone.padc_x_travel_app_aps.utils.ACCESS_TOKEN

object TourModelImpl:BaseModel(),TourModel {

    val mCountryVORepository:HashMap<String,CountryVO> = hashMapOf()
    override fun getAllTours(onSuccess: (dataVo: DataVO) -> Unit, onFailure: (String) -> Unit) {

            mDataAgent.getAllTours(
                ACCESS_TOKEN,
                onSuccess ={dataVo->
                    dataVo.country.forEach{ countries->

                        mCountryVORepository[countries.name]= countries
                    }
                    dataVo.popular_tour.forEach {populartours->
                        mCountryVORepository[populartours.name]= populartours
                    }
                    onSuccess.invoke(dataVo)
                },
                onFailure = {
                    onFailure.invoke(it)
                })

    }


    override fun getTourByPosition(name: String): CountryVO {
        mCountryVORepository[name]?.let {
            return it
        }
        return CountryVO()
    }


}
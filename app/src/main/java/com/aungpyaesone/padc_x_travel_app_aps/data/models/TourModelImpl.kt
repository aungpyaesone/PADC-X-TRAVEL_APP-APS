package com.aungpyaesone.padc_x_travel_app_aps.data.models

import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.utils.ACCESS_TOKEN

object TourModelImpl:BaseModel(),TourModel {

    val mCountryVORepository:HashMap<String,CountryVO> = hashMapOf()

    override fun getAllTours(
        onSuccess: (List<CountryVO>, List<CountryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    mDataAgent.getAllTours(
        ACCESS_TOKEN,
        onSuccess ={countryList,popularTourList->
            countryList.forEach{ countries->

               mCountryVORepository[countries.name]= countries
            }
            popularTourList.forEach {populartours->
                mCountryVORepository[populartours.name]= populartours
            }
            onSuccess.invoke(countryList, popularTourList)
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
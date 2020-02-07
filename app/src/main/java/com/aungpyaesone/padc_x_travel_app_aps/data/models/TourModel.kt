package com.aungpyaesone.padc_x_travel_app_aps.data.models

import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.DataVO
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.PopularTourVO

interface TourModel {

    fun getAllTours(
        onSuccess:(List<CountryVO>,List<CountryVO>)->Unit,
                    onFailure: (String)->Unit)

    fun getTourByPosition(name:String):CountryVO
}
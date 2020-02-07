package com.aungpyaesone.padc_x_travel_app_aps.network.data_agent

import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.DataVO
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.PopularTourVO

interface TourDataAgent {

    fun getAllTours(accessToken:String,onSuccess: (List<CountryVO>,List<CountryVO>)->Unit,
                    onFailure: (String)->Unit)

}
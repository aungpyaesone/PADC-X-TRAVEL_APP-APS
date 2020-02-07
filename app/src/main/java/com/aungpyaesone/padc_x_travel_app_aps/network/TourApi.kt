package com.aungpyaesone.padc_x_travel_app_aps.network

import com.aungpyaesone.padc_x_travel_app_aps.network.response.GetAllTourResponse
import com.aungpyaesone.padc_x_travel_app_aps.utils.ACCESS_TOKEN
import com.aungpyaesone.padc_x_travel_app_aps.utils.END_POINT
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

interface TourApi {

    @GET(END_POINT)
    fun getAllTours(): Call<GetAllTourResponse>
}
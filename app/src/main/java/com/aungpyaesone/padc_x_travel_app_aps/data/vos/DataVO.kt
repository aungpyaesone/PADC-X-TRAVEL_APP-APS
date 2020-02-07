package com.aungpyaesone.padc_x_travel_app_aps.data.vos

import com.google.gson.annotations.SerializedName

data class DataVO(
    @SerializedName("country")val country:ArrayList<CountryVO> = arrayListOf(),
    @SerializedName("popular_tours")val popular_tour:ArrayList<CountryVO> = arrayListOf()
)
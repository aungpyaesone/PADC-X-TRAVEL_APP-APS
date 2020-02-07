package com.aungpyaesone.padc_x_travel_app_aps.network.response

import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.DataVO
import com.aungpyaesone.padc_x_travel_app_aps.utils.CODE_RESPONSE
import com.google.gson.annotations.SerializedName

data class GetAllTourResponse(
    @SerializedName("code")val code: Int = 0,
    @SerializedName("message")val message: String = "",
    @SerializedName("data")val data:DataVO? = null
)

{
    fun isResponseOk():Boolean{
    return data !=null && code == CODE_RESPONSE
    }

}
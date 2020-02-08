package com.aungpyaesone.padc_x_travel_app_aps.network.data_agent

import android.os.AsyncTask
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.DataVO
import com.aungpyaesone.padc_x_travel_app_aps.network.response.GetAllTourResponse
import com.aungpyaesone.padc_x_travel_app_aps.utils.ACCESS_TOKEN
import com.aungpyaesone.padc_x_travel_app_aps.utils.BASE_URL
import com.aungpyaesone.padc_x_travel_app_aps.utils.END_POINT
import com.aungpyaesone.padc_x_travel_app_aps.utils.EN_CONNECTION_ERROR
import com.google.gson.Gson
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

object OkhttpDataAgentImpl : TourDataAgent {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    override fun getAllTours(
        accessToken: String,
        onSuccess: (dataVO: DataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetAllTours(
            accessToken=accessToken,
            mOkHttpClient = okHttpClient,
            onSuccess = onSuccess,
            onFailure = onFailure).execute()
    }

    class GetAllTours(
        private val accessToken: String,
        private val mOkHttpClient: OkHttpClient,
        private val onSuccess: (dataVO:DataVO) -> Unit,
        private val onFailure: (String) -> Unit
        ): AsyncTask<Void,Void,GetAllTourResponse>(){

        override fun doInBackground(vararg params: Void?): GetAllTourResponse? {
            val formBody = FormBody.Builder()
                .add(ACCESS_TOKEN,accessToken)
                .build()

            val request = Request.Builder()
                .url(BASE_URL + END_POINT)
                .get()
                .build()
            try{
                val response = mOkHttpClient
                    .newCall(request)
                    .execute()
                if(response.isSuccessful)
                {
                    response.body?.let {
                        val responseString = it.string()
                        return Gson().fromJson<GetAllTourResponse>(
                            responseString,
                            GetAllTourResponse::class.java
                        )
                    }
                }
            }
            catch (e:Exception){

            }
            return null
        }

        override fun onPostExecute(result: GetAllTourResponse?) {
            super.onPostExecute(result)

            if(result !=null){
                if(result.isResponseOk()){
                    result.data?.let {
                        onSuccess(it)
                    }
                }else{
                    onFailure(result.message)
                }
            }
            else{
                onFailure(EN_CONNECTION_ERROR)
            }

        }
    }
}
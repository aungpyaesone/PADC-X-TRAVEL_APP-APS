package com.aungpyaesone.padc_x_travel_app_aps.network.data_agent

import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.DataVO
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.PopularTourVO
import com.aungpyaesone.padc_x_travel_app_aps.network.TourApi
import com.aungpyaesone.padc_x_travel_app_aps.network.response.GetAllTourResponse
import com.aungpyaesone.padc_x_travel_app_aps.utils.BASE_URL
import com.aungpyaesone.padc_x_travel_app_aps.utils.EN_CONNECTION_ERROR
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : TourDataAgent {

    private var mTourApi: TourApi? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
        mTourApi = retrofit.create(TourApi::class.java)

    }

    override fun getAllTours(
        accessToken: String,
        onSuccess: (dataVO: DataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val getAllTour = mTourApi?.getAllTours()
        getAllTour?.enqueue(object : Callback<GetAllTourResponse> {
            override fun onFailure(call: Call<GetAllTourResponse>, t: Throwable) {
                onFailure(t.message ?: EN_CONNECTION_ERROR)
            }

            override fun onResponse(
                call: Call<GetAllTourResponse>,
                response: Response<GetAllTourResponse>
            ) {
                val getAllTourResponse = response.body()
                if(getAllTourResponse != null)
                {
                    if(getAllTourResponse.isResponseOk()){
                        getAllTourResponse.data?.let {
                            onSuccess(it)
                        }
                    }
                    else
                    {
                        onFailure(getAllTourResponse.message)
                    }
                }else{
                    onFailure(EN_CONNECTION_ERROR)
                }
            }
        })
    }
    }
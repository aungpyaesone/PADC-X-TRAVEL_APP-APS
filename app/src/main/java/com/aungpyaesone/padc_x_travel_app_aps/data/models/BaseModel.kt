package com.aungpyaesone.padc_x_travel_app_aps.data.models

import com.aungpyaesone.padc_x_travel_app_aps.network.data_agent.HttpUrlConnectionDataAgentImpl
import com.aungpyaesone.padc_x_travel_app_aps.network.data_agent.OkhttpDataAgentImpl
import com.aungpyaesone.padc_x_travel_app_aps.network.data_agent.RetrofitDataAgentImpl
import com.aungpyaesone.padc_x_travel_app_aps.network.data_agent.TourDataAgent

abstract class BaseModel {
    var mDataAgent:TourDataAgent = RetrofitDataAgentImpl
}
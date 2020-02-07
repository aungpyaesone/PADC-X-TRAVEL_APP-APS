package com.aungpyaesone.padc_x_travel_app_aps.data.vos

import com.google.gson.annotations.SerializedName

data class CountryVO(
    @SerializedName("name")val name:String ="",
    @SerializedName("description")val description:String ="",
    @SerializedName("location")val location:String ="",
    @SerializedName("average_rating")val average_rating:Double =0.0,
    @SerializedName("scores_and_reviews")val scores_and_reviews:ArrayList<ScoreandReviewVO> = arrayListOf(),
    @SerializedName("photos")val photos:ArrayList<String> = arrayListOf()
)

package com.aungpyaesone.padc_x_travel_app_aps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aungpyaesone.padc_x_travel_app_aps.R
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.ScoreandReviewVO
import com.aungpyaesone.padc_x_travel_app_aps.view.viewholder.BaseViewHolder
import com.aungpyaesone.padc_x_travel_app_aps.view.viewholder.ScoreandReviewViewHolder

class ScoreandReviewAdapter: BaseAdapter<BaseViewHolder<ScoreandReviewVO>,ScoreandReviewVO>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ScoreandReviewVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sorce_and_review_item_view,parent,false)
        return ScoreandReviewViewHolder(view)
    }
}
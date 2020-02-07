package com.aungpyaesone.padc_x_travel_app_aps.view.viewholder

import android.annotation.SuppressLint
import android.view.View
import com.aungpyaesone.padc_x_travel_app_aps.R
import com.aungpyaesone.padc_x_travel_app_aps.data.vos.ScoreandReviewVO
import kotlinx.android.synthetic.main.sorce_and_review_item_view.view.*

class ScoreandReviewViewHolder(itemView: View) : BaseViewHolder<ScoreandReviewVO>(itemView) {
    @SuppressLint("SetTextI18n")
    override fun bindData(data: ScoreandReviewVO) {
        mData = data

       /* if(mData.s)
        val imgArray:ArrayList<Int> = arrayListOf(R.drawable.booking,R.drawable.ic_message_blue_24dp)
        imgArray.forEach {

        }*/
        val score = data.score.toString()
        val max_score = data.max_score.toString()
        val review = data.total_reviews.toString()
        itemView.tvBooking.text = data.name
        itemView.tvScore.text =score+"/"+max_score
        itemView.tvReview.text = "Base on $review reviews"
    }
}
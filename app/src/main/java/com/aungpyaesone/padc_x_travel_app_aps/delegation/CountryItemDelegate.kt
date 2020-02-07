package com.aungpyaesone.padc_x_travel_app_aps.delegation

import com.aungpyaesone.padc_x_travel_app_aps.data.vos.CountryVO

interface CountryItemDelegate {
    fun onTouchCountryItem(name:String)
}
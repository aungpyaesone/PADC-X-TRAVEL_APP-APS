package com.aungpyaesone.padc_x_travel_app_aps.fragment

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    protected fun showSnackbar(message:String){
        activity?.window?.decorView?.let { Snackbar.make(it,message,Snackbar.LENGTH_SHORT).show() }
    }
}
package com.aungpyaesone.padc_x_travel_app_aps.activities

fun main(args:Array<String>){
    val str =  "Jalan HOS Cokroaminoto No.79, Menteng, 10310 Jakarta, Indonesia "
/*
    println(str.takeLastWhile {
        if(!it.isLetter()){
            it.isIdentifierIgnorable()
           // it.isLetter()
        }
        it.isLetter()
    })*/
    var data=str.trimEnd()

    println(data.takeLastWhile {
        it.isLetter()
    })
}
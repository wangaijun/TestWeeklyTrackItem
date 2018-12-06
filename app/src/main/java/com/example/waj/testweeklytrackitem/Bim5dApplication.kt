package com.example.waj.testweeklytrackitem

import android.app.Application

class Bim5dApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        var instance:Bim5dApplication = Bim5dApplication()
//        fun getInstance():Bim5dApplication{
//
//        }
    }
}
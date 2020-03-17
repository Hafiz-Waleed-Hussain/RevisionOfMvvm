package com.uwantolearn.rapidapiassignment.app

import android.app.Application
import com.uwantolearn.api.di.RetrofitModule
import com.uwantolearn.rapidapiassignment.di.AppComponent
import com.uwantolearn.rapidapiassignment.di.DaggerAppComponent

class RapidApiApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .retrofitModule(RetrofitModule(""))
            .build()
    }

}
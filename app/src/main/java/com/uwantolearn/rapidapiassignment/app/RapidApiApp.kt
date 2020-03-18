package com.uwantolearn.rapidapiassignment.app

import android.app.Application
import com.uwantolearn.api.di.RetrofitModule
import com.uwantolearn.rapidapiassignment.R
import com.uwantolearn.rapidapiassignment.di.AppComponent
import com.uwantolearn.rapidapiassignment.di.AppExecutorModule
import com.uwantolearn.rapidapiassignment.di.AppModule
import com.uwantolearn.rapidapiassignment.di.DaggerAppComponent

class RapidApiApp : Application() {

    val appComponent: AppComponent by lazy {
        RetrofitModule(
            url = getString(R.string.url),
            headerHost = getString(R.string.headerHost),
            headerKey = getString(R.string.header_key)
        ).let {
            DaggerAppComponent.builder()
                .retrofitModule(it)
                .appModule(AppModule(this))
                .build()
        }
    }
}
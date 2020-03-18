package com.uwantolearn.rapidapiassignment.di

import com.uwantolearn.rapidapiassignment.extensions.appComponent
import com.uwantolearn.rapidapiassignment.feature.home.HomeActivity
import com.uwantolearn.rapidapiassignment.feature.home.di.DaggerHomeComponent
import com.uwantolearn.rapidapiassignment.feature.home.di.HomeModule

fun HomeActivity.inject() {
    DaggerHomeComponent.builder()
        .appComponent(application.appComponent())
        .homeModule(HomeModule(this))
        .build()
        .inject(this)
}
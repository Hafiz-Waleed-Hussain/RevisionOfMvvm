package com.uwantolearn.rapidapiassignment.extensions

import android.app.Application
import com.uwantolearn.rapidapiassignment.app.RapidApiApp
import com.uwantolearn.rapidapiassignment.di.AppComponent

fun Application.appComponent(): AppComponent = (this as RapidApiApp).appComponent
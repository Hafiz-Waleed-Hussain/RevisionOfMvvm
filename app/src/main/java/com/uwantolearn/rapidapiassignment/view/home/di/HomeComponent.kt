package com.uwantolearn.rapidapiassignment.view.home.di

import com.uwantolearn.rapidapiassignment.di.AppComponent
import com.uwantolearn.rapidapiassignment.di.scopes.ActivityScope
import com.uwantolearn.rapidapiassignment.view.home.HomeActivity
import dagger.Component
import dagger.Module

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [HomeModule::class])
interface HomeComponent {
    fun inject(activity: HomeActivity)
}

@Module
class HomeModule {

}


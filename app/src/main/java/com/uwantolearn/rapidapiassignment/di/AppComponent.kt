package com.uwantolearn.rapidapiassignment.di

import com.uwantolearn.api.di.RetrofitModule
import com.uwantolearn.rapidapiassignment.api.source.RapidApiDataSource
import com.uwantolearn.rapidapiassignment.api.source.repositories.RapidAPIRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun rapidAPIRepository(): RapidApiDataSource
}


@Module(includes = [RetrofitModule::class])
class AppModule
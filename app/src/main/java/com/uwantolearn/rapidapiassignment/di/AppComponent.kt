package com.uwantolearn.rapidapiassignment.di

import android.content.Context
import com.uwantolearn.api.di.RetrofitModule
import com.uwantolearn.rapidapiassignment.data.repositories.RapidAPIRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun rapidAPIRepository(): RapidAPIRepository
}

@Module(
    includes = [
        RetrofitModule::class,
        AppExecutorModule::class,
        RapidApiDataSourceModule::class
    ]
)

class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideAppContext(): Context = context
}


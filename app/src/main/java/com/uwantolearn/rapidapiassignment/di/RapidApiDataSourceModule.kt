package com.uwantolearn.rapidapiassignment.di

import android.content.Context
import com.uwantolearn.rapidapiassignment.data.repositories.RapidAPIRepository
import com.uwantolearn.rapidapiassignment.data.source.remote.RemoteRapidApi
import com.uwantolearn.rapidapiassignment.database.LocalRapidApi
import com.uwantolearn.rapidapiassignment.database.RapidImageDatabase
import com.uwantolearn.rapidapiassignment.database.dao.RapidImageDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RapidApiDataSourceModule {


    @Singleton
    @Provides
    internal fun provideRapidImageDAO(context: Context): RapidImageDAO {
        return RapidImageDatabase.getInstance(context).imagesDAO()
    }

}
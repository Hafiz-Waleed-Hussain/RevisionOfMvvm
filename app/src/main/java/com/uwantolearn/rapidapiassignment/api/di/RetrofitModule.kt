package com.uwantolearn.api.di

import com.uwantolearn.api.retrofit.service.RapidAPIRetrofitService
import com.uwantolearn.rapidapiassignment.BuildConfig
import com.uwantolearn.rapidapiassignment.api.source.RapidApiDataSource
import com.uwantolearn.rapidapiassignment.api.source.remote.RemoteRapidApiDataDataSource
import com.uwantolearn.rapidapiassignment.api.source.repositories.RapidAPIRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

private const val READ_TIMEOUT = 3L
private const val CONNECT_TIMEOUT = 3L

@Module
class RetrofitModule(private val url: String) {

    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    internal fun provideOkHttpClient(loggingInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    internal fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    internal fun provideRapidDataSource(remote: RemoteRapidApiDataDataSource): RapidApiDataSource {
        return RapidAPIRepository(remote)
    }

    @Provides
    internal fun provideRemoteRapidDataSource(service: RapidAPIRetrofitService): RemoteRapidApiDataDataSource {
        return RemoteRapidApiDataDataSource(service)
    }

    @Provides
    internal fun provideRapidAPIRetrofitService(retrofit: Retrofit): RapidAPIRetrofitService {
        return retrofit.create(RapidAPIRetrofitService::class.java)
    }

}
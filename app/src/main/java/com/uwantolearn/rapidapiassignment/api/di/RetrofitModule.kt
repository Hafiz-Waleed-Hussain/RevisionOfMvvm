package com.uwantolearn.api.di

import com.uwantolearn.api.retrofit.service.RapidAPIRetrofitService
import com.uwantolearn.rapidapiassignment.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val READ_TIMEOUT = 3L
private const val CONNECT_TIMEOUT = 3L

@Module
class RetrofitModule(
    private val url: String,
    private val headerHost: String,
    private val headerKey: String
) {

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    internal fun provideOkHttpClient(
        @Named("loggingInterceptor") loggingInterceptor: Interceptor,
        @Named("headerInterceptor") headerInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()

    @Singleton
    @Provides
    @Named("headerInterceptor")
    internal fun provideHeaderInterceptor(): Interceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("x-rapidapi-host", headerHost)
            .addHeader("x-rapidapi-key", headerKey)
            .build()
        chain.proceed(newRequest)
    }

    @Singleton
    @Provides
    @Named("loggingInterceptor")
    internal fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    @Singleton
    @Provides
    internal fun provideRapidAPIRetrofitService(retrofit: Retrofit): RapidAPIRetrofitService {
        return retrofit.create(RapidAPIRetrofitService::class.java)
    }

}
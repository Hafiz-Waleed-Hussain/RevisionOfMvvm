package com.uwantolearn.rapidapiassignment.di

import android.os.Handler
import android.os.Looper
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppExecutorModule {

    @Singleton
    @Provides
    @Named("mainExecutor")
    fun provideMainExecutor(): Executor = object : Executor {
        private val mainHandler: Handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainHandler.post(command)
        }
    }

    @Singleton
    @Provides
    @Named("ioExecutor")
    fun provideIOExecutor(): Executor = (Runtime.getRuntime().availableProcessors() * 2)
        .let(Executors::newFixedThreadPool)
}
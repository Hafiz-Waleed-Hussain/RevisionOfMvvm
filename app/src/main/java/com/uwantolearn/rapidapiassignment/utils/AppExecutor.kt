package com.uwantolearn.rapidapiassignment.utils

import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Named

class AppExecutor @Inject constructor(
    @Named("mainExecutor") private val mainExecutor: Executor,
    @Named("ioExecutor") private val ioExecutor: Executor
) {
    fun mainExecutor(): Executor = mainExecutor
    fun ioExecutor(): Executor = ioExecutor
}
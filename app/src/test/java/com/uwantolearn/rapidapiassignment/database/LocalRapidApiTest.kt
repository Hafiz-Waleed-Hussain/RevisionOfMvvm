package com.uwantolearn.rapidapiassignment.database

import com.nhaarman.mockito_kotlin.verify
import com.uwantolearn.rapidapiassignment.database.dao.RapidImageDAO
import com.uwantolearn.rapidapiassignment.model.RapidImage
import com.uwantolearn.rapidapiassignment.utils.AppExecutor
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class LocalRapidApiTest {
    @Mock
    lateinit var dao: RapidImageDAO
    @Mock
    lateinit var complete: () -> Unit

    val executor: Executor = Executor { it.run() }
    val appExecutor: AppExecutor = AppExecutor(executor, executor)

    private lateinit var localRapidApi: LocalRapidApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        localRapidApi = LocalRapidApi(dao, appExecutor)
    }

    @Test
    fun `when insert data should updated againstQuery field with query text`() {
        var o = RapidImage(id = 1, againstQuery = "")
        localRapidApi.insert("abc", listOf(o), complete)
        assertThat(o.againstQuery, `is`(equalTo("abc")))
        verify(complete).invoke()
    }

    @Test
    fun `when imagesAgainstQUery invoked verify loadImages of DAO`() {
        localRapidApi.imagesAgainstQuery("abc")
        verify(dao).loadImages("abc")
    }

}
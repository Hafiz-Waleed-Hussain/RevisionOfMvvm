package com.uwantolearn.rapidapiassignment.database.dao


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.uwantolearn.rapidapiassignment.database.RapidImageDatabase
import com.uwantolearn.rapidapiassignment.model.RapidImage
import com.uwantolearn.rapidapiassignment.model.RapidImageQuery
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RapidImageDAOTest {

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: RapidImageDatabase

    private lateinit var dao: RapidImageDAO

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            RapidImageDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = database.imagesDAO()
    }

    @Test
    fun when_there_is_no_data_against_query_should_return_empty() {
        dao.loadImages("")
            .test()
            .assertHasValue()
            .assertValue { it.isEmpty() }
    }

    @Test
    fun insert_one_data_object_against_query_should_return_only_one_data() {
        val query = RapidImageQuery("cat")
        val firstImage = RapidImage(id = 1, againstQuery = "cat")
        dao.insert(query)
        dao.insert(firstImage)

        dao.loadImages("cat")
            .test()
            .assertHasValue()
            .assertValue { it[0].images.size == 1 }
    }


    @Test
    fun insert_batch_of_data_and_load_all_should_return_all_data() {
        val query = RapidImageQuery("cat")
        val list = Array(13) { RapidImage(id = it.toLong(), againstQuery = "cat") }
        dao.insert(query)
        dao.insert(*list)

        dao.loadImages("cat")
            .test()
            .assertHasValue()
            .assertValue { it[0].images.size == 12 }
    }


    @After
    fun release() {
        database.clearAllTables()
        database.close()
    }

}
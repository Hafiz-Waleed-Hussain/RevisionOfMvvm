package com.uwantolearn.rapidapiassignment.database.dao


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.uwantolearn.rapidapiassignment.database.RapidImageDatabase
import com.uwantolearn.rapidapiassignment.model.RapidImage
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
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
        loadDataFromDB("cat")
            .isEmpty()
            .let(::assertTrue)
    }

    @Test
    fun insert_one_data_object_against_query_should_return_only_one_data() {
        val firstImage = RapidImage(id = 1, againstQuery = "cat")
        val secondImage = RapidImage(id = 2, againstQuery = "dog")
        dao.insert(listOf(firstImage, secondImage))

        loadDataFromDB("cat")
            .size
            .let { assertThat(it, `is`(CoreMatchers.equalTo(1))) }
    }

    @Test
    fun insert_batch_of_data_and_load_all_should_return_all_data() {
        Array(13) { RapidImage(id = (it+1).toLong(), againstQuery = "cat") }
            .toList()
            .let(dao::insert)

        loadDataFromDB("cat")
            .size
            .let { assertThat(it, `is`(CoreMatchers.equalTo(13))) }
    }

    @Test
    fun insert_data_against_different_queries_and_then_validate_query_should_return_against_query() {
        val catImages = Array(8) { RapidImage(id = (it+1).toLong(), againstQuery = "cat") }
        val dogImages = Array(5) { RapidImage(id = (it + 10).toLong(), againstQuery = "dog") }
        val cowImages = Array(3) { RapidImage(id = (it + 20).toLong(), againstQuery = "cow") }

        (catImages + dogImages + cowImages).toList()
            .let(dao::insert)

        loadDataFromDB("cat")
            .size
            .let { assertThat(it, `is`(CoreMatchers.equalTo(8))) }
        loadDataFromDB("dog")
            .size
            .let { assertThat(it, `is`(CoreMatchers.equalTo(5))) }
        loadDataFromDB("cow")
            .size
            .let { assertThat(it, `is`(CoreMatchers.equalTo(3))) }
    }


    @After
    fun release() {
        database.clearAllTables()
        database.close()
    }

    private fun loadDataFromDB(query: String) =
        (dao.loadImages(query).create() as LimitOffsetDataSource)
            .loadRange(0, 100)
}
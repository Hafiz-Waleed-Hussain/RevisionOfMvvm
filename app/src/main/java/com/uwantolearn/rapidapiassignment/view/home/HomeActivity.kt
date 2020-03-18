package com.uwantolearn.rapidapiassignment.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uwantolearn.rapidapiassignment.R
import com.uwantolearn.rapidapiassignment.extensions.appComponent
import com.uwantolearn.rapidapiassignment.view.home.di.DaggerHomeComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

//    @Inject
//    lateinit var repo: RapidApiDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerHomeComponent.builder()
            .appComponent(application.appComponent())
            .build()
            .inject(this)

        var counter = 1
        button.setOnClickListener {
//            repo.loadImages("Taylor Swift", counter)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    {
//                        println(it)
//                    },
//                    {
//                        println(it)
//                    })
            counter++
        }
    }
}

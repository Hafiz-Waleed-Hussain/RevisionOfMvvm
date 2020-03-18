package com.uwantolearn.rapidapiassignment.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.uwantolearn.rapidapiassignment.R
import com.uwantolearn.rapidapiassignment.extensions.appComponent
import com.uwantolearn.rapidapiassignment.factories.ViewModelFactory
import com.uwantolearn.rapidapiassignment.view.home.di.DaggerHomeComponent
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val adapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        DaggerHomeComponent.builder()
            .appComponent(application.appComponent())
            .build()
            .inject(this)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        val layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = adapter

        viewModel.images.observe(this, Observer {
            adapter.submitList(it)
        })


        var counter = 1
        search.setOnClickListener {
            viewModel.searchImage("A")

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

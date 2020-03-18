package com.uwantolearn.rapidapiassignment.feature.home

import android.os.Bundle
import android.text.Layout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uwantolearn.rapidapiassignment.R
import com.uwantolearn.rapidapiassignment.di.inject
import com.uwantolearn.rapidapiassignment.extensions.appComponent
import com.uwantolearn.rapidapiassignment.factories.ViewModelFactory
import com.uwantolearn.rapidapiassignment.feature.home.di.DaggerHomeComponent
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var adapter: HomeAdapter
    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        inject()

        val viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        recyclerview.init()

        viewModel.images.observe(this, Observer(adapter::submitList))

        search.setOnClickListener { viewModel.searchImage(editText.text.toString()) }
    }

    private fun RecyclerView.init() {
        this.layoutManager = this@HomeActivity.layoutManager
        this.adapter = this@HomeActivity.adapter
    }
}

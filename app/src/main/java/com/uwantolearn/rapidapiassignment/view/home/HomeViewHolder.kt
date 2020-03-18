package com.uwantolearn.rapidapiassignment.view.home

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uwantolearn.rapidapiassignment.model.RapidImagesAgainstQuery

class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var rapidImage: RapidImagesAgainstQuery? = null

    init {
        view.setOnClickListener {
            // This is a bad approach Glide should not be used as a hidden dependency.
            rapidImage?.let {
                Glide.with(view)
                    .load(it.image.url)
                    .into(view as ImageView)
            }
        }
    }

    fun bind(data: RapidImagesAgainstQuery) {
        this.rapidImage = data
    }
}
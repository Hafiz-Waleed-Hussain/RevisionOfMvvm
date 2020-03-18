package com.uwantolearn.rapidapiassignment.view.home

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uwantolearn.rapidapiassignment.R
import com.uwantolearn.rapidapiassignment.extensions.inflate
import com.uwantolearn.rapidapiassignment.model.RapidImage
import com.uwantolearn.rapidapiassignment.model.RapidImagesAgainstQuery
import javax.inject.Inject

class HomeAdapter @Inject constructor() :
    PagedListAdapter<RapidImagesAgainstQuery, RecyclerView.ViewHolder>(diffUtilImpl) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        parent.inflate(R.layout.cell_image).let(::HomeViewHolder)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let((holder as HomeViewHolder)::bind)
    }
}

private val diffUtilImpl = object : DiffUtil.ItemCallback<RapidImagesAgainstQuery>() {
    override fun areItemsTheSame(
        oldItem: RapidImagesAgainstQuery,
        newItem: RapidImagesAgainstQuery
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: RapidImagesAgainstQuery,
        newItem: RapidImagesAgainstQuery
    ): Boolean = oldItem == newItem

}

package com.uwantolearn.rapidapiassignment.feature.home

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.uwantolearn.rapidapiassignment.R
import com.uwantolearn.rapidapiassignment.extensions.inflate
import com.uwantolearn.rapidapiassignment.model.RapidImage
import javax.inject.Inject

class HomeAdapter @Inject constructor() :
    PagedListAdapter<RapidImage, RecyclerView.ViewHolder>(diffUtilImpl) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        parent.inflate(R.layout.cell_image).let(::HomeViewHolder)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let((holder as HomeViewHolder)::bind)
    }
}

private val diffUtilImpl = object : DiffUtil.ItemCallback<RapidImage>() {
    override fun areItemsTheSame(
        oldItem: RapidImage,
        newItem: RapidImage
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: RapidImage,
        newItem: RapidImage
    ): Boolean = oldItem == newItem
}

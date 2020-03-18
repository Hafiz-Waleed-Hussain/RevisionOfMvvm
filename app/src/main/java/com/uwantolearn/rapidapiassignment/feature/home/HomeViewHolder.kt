package com.uwantolearn.rapidapiassignment.feature.home

import android.app.Dialog
import android.content.Context
import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uwantolearn.rapidapiassignment.model.RapidImage
import kotlinx.android.synthetic.main.cell_image.view.*


class HomeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var rapidImage: RapidImage

    init {
        view.setOnClickListener { showPopup() }
    }

    fun bind(data: RapidImage) {
        this.rapidImage = data
        view.imageView.loadImage(rapidImage.thumbnail)
    }

    private fun showPopup() {
        view.context.let(::createImageView)
            .also { it.loadImage(rapidImage.thumbnail) }
            .let(::createDialog)
            .show()
    }

    private fun createDialog(imageView: ImageView): Dialog = Dialog(imageView.context)
        .apply { setContentView(imageView) }

    private fun createImageView(context: Context): ImageView = ImageView(context).apply {
        layoutParams = LinearLayout.LayoutParams(rapidImage.width, rapidImage.height)
        scaleType = ImageView.ScaleType.CENTER
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}


package com.martakonik.sentiacodetest.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.martakonik.sentiacodetest.R

fun ImageView.loadImageWithUrl(url: String, @DrawableRes drawableErrorId: Int) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .error(drawableErrorId)
        .into(this)
}

fun ImageView.loadAvatarCircularImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_baseline_people_24)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}
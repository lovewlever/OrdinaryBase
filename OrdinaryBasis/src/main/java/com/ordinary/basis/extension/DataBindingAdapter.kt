package com.ordinary.basis.extension

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * 图片加载
 * @see
 * @author 01218
 * @version 1.0
 * @date 20-2-27
 */
@BindingAdapter(value = ["app:loadImageForUrl"])
fun loadImageForUrl(imageView: ImageView, url: String) {
    if (url != "") {
        val suffix = url.substring(url.lastIndexOf(".") + 1)
        if (suffix == "GIF" || suffix == "gif") {
            Glide.with(imageView.context).asGif()
                .load(url).thumbnail(0.2F).into(imageView)
        } else {
            Glide.with(imageView.context).asBitmap()
                .load(url).thumbnail(0.2F).into(imageView)
        }
    }
}

@BindingAdapter(value = ["app:textColorString"])
fun setTextViewTextColor(tv: TextView, color: String) {
    try {
        val parseColor = Color.parseColor(color)
        tv.setTextColor(parseColor)
    } catch (e: Exception) {
        tv.setTextColor(Color.BLACK)
    }

}






package com.ordinary.basis.extension

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ordinary.basis.ui.newUnionAdapter
import org.jetbrains.annotations.NotNull

/**
 * 图片加载
 * @see
 * @author 01218
 * @version 1.0
 * @date 20-2-27
 */
@BindingAdapter(value = ["loadImageForUrl"])
fun loadImageForUrl(imageView: ImageView, url: String) {
    if (url != "") {
        val suffix = url.substring(url.lastIndexOf(".") + 1)
        if (suffix == "GIF" || suffix == "gif") {
            imageView.context?.let { ctx ->
                Glide.with(ctx).asGif()
                    .load(url).thumbnail(0.2F).into(imageView)
            }
        } else {
            Glide.with(imageView.context).asBitmap()
                .load(url).thumbnail(0.2F).into(imageView)
        }
    }
}

@BindingAdapter(value = ["textColorString"])
fun setTextViewTextColor(tv: TextView, color: String) {
    try {
        val parseColor = Color.parseColor(color)
        tv.setTextColor(parseColor)
    } catch (e: Exception) {
        tv.setTextColor(Color.BLACK)
    }
}






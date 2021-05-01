package kg.turar.arykbaev.letstalk.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import kg.turar.arykbaev.letstalk.R

fun ImageView.setImageByUrl(url: String) {
    Glide
        .with(context)
        .load(url)
        .placeholder(R.drawable.person)
        .into(this)
}
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

fun ImageView.setImageByName(name: String, resType: String = "drawable") {
    val resId = resources.getIdentifier(name, resType, context.packageName)
    this.setImageResource(resId)
}
package kg.turar.arykbaev.letstalk.extension

import android.widget.TextView

val TextView.toText: String
    get() = text.toString()
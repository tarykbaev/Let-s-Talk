package kg.turar.arykbaev.letstalk.extension

import java.text.SimpleDateFormat
import java.util.*


fun String.isValidEmail(): Boolean {
    return true
    //(this.count() == 23) && (this.substringAfterLast("@") == "manas.edu.kg") && (this.count { it == '@' } == 1)
}

fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}

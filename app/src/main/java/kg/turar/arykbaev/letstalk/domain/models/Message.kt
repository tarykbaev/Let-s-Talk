package kg.turar.arykbaev.letstalk.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(
    var text: String = "",
    var type: String = "",
    var from: String = "",
    var timeStamp: Long = 0,
    var isCurrent: Boolean = false
): Parcelable

package kg.turar.arykbaev.letstalk.domain

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var department: String = "",
    var grade: String = "",
    var gender: String = "",
    var uri: Uri? = null,
    var from: String = "",
    var nativeLang: String = "",
    var learningLang: String = ""
): Parcelable
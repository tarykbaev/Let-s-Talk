package kg.turar.arykbaev.letstalk.domain.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var department: String = "",
    var grade: String = "",
    var gender: String = "",
    var uri: Uri? = null,
    var image_url: String = "",
    var from: String = "",
    var nativeLang: String = "",
    var learningLang: String = "",
    var state: String = ""
): Parcelable
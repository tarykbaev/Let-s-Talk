package kg.turar.arykbaev.letstalk.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class From(val description: String): Parcelable

@Parcelize
data class NativeLanguage(val description: String): Parcelable

@Parcelize
data class LearningLanguage(val description: String): Parcelable
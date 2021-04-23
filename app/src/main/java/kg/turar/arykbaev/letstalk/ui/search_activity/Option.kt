package kg.turar.arykbaev.letstalk.ui.search_activity

import android.os.Parcel
import android.os.Parcelable

class Option<T : Parcelable>(val item: T, val description: String, var highlightChars: Int = 0) :
    Parcelable {
    constructor(parcel: Parcel) :
            this(
                parcel.readParcelable(
                    Option<T>::item.javaClass.classLoader)!!,
                parcel.readString()!!,
                parcel.readInt()
            )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(item, flags)
        parcel.writeString(description)
        parcel.writeInt(highlightChars)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Option<Parcelable>> {
        override fun createFromParcel(parcel: Parcel): Option<Parcelable> {
            return Option(parcel)
        }

        override fun newArray(size: Int): Array<Option<Parcelable>?> {
            return arrayOfNulls(size)
        }
    }
}
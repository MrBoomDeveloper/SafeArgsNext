package com.mrboomdev.safeargsnext.value

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RestrictTo

/**
 * Literally serialized values are being held here.
 * Actual structure of the object may vary depending on it's internals.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
class JsonValueWrapper(
    val clazz: Class<*>, 
    val json: String
): Parcelable {
    @Suppress("DEPRECATION")
    constructor(parcel: Parcel) : this(
        parcel.readSerializable() as Class<*>,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(clazz)
        parcel.writeString(json)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<JsonValueWrapper> {
        override fun createFromParcel(parcel: Parcel) = JsonValueWrapper(parcel)
        override fun newArray(size: Int) = arrayOfNulls<JsonValueWrapper>(size)
    }
}
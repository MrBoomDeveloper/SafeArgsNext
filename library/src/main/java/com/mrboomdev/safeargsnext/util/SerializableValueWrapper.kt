package com.mrboomdev.safeargsnext.util

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * This class is supposed to be used by the library to pack classes,
 * which are having a different type after serialization made by the framework.
 */
internal class SerializableValueWrapper : Parcelable {
	private val serializable: Serializable?
	private val parcelable: Parcelable?

	val value: Any
		get() {
			return (serializable ?: parcelable)!!
		}

	constructor(value: Any) {
		when(value) {
			is Parcelable -> {
				this.parcelable = value
				this.serializable = null
			}

			is Serializable -> {
				this.serializable = value
				this.parcelable = null
			}

			else -> throw UnsupportedOperationException("You can't serialize this object!")
		}
	}

	constructor(parcelable: Parcelable) {
		this.parcelable = parcelable
		this.serializable = null
	}

	@Suppress("DEPRECATION")
	constructor(parcel: Parcel) {
		this.parcelable = parcel.readParcelable(javaClass.classLoader)
		this.serializable = parcel.readSerializable()

		if(serializable == null && parcelable == null) {
			throw IllegalArgumentException("Both arguments are null, which is an illegal usage.")
		}
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeParcelable(parcelable, 0)
		parcel.writeSerializable(serializable)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<SerializableValueWrapper> {
		override fun createFromParcel(parcel: Parcel): SerializableValueWrapper {
			return SerializableValueWrapper(parcel)
		}

		override fun newArray(size: Int): Array<SerializableValueWrapper?> {
			return arrayOfNulls(size)
		}
	}
}
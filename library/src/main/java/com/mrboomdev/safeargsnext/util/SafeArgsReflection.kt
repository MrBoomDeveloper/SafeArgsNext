package com.mrboomdev.safeargsnext.util

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.mrboomdev.safeargsnext.SafeArgs
import java.io.Serializable
import java.lang.reflect.Method

object SafeArgsReflection {

	// The irony of the fact that an SafeArgsCreator is using Unsafe
	private val unsafe: Any by lazy {
		(Class.forName("sun.misc.Unsafe") as Class<*>)
			.getDeclaredField("theUnsafe")
			.apply { isAccessible = true }
			.get(null)
	}

	private val allocateInstanceMethod: Method by lazy {
		unsafe.javaClass.getDeclaredMethod("allocateInstance", Class::class.java).apply {
			isAccessible = true
		}
	}

	@Suppress("UNCHECKED_CAST", "DEPRECATION")
	fun <T> restoreSafeArgs(clazz: Class<T>, bundle: Bundle?): T? {
		if(bundle == null) {
			return null
		}

		val instance = allocateInstanceMethod.invoke(unsafe, clazz) as T

		for(key in bundle.keySet()) {
			val value = bundle.get(key)

			try {
				clazz.getDeclaredField(key).apply {
					isAccessible = true

					if(type == String::class.java && value !is String && value is CharSequence) {
						set(instance, value.toString())
					} else {
						set(instance, value)
					}
				}
			} catch(_: NoSuchFieldError) {}
		}

		return instance
	}

	@Suppress("UNCHECKED_CAST")
	fun fillWithSafeArgs(bundle: Bundle, safeArgs: Any) {
		for(field in safeArgs.javaClass.declaredFields) {
			field.isAccessible = true
			val value = field.get(safeArgs) ?: continue

			when(value::class) {
				String::class -> bundle.putString(field.name, value as String)
				Int::class -> bundle.putInt(field.name, value as Int)
				Float::class -> bundle.putFloat(field.name, value as Float)
				Double::class -> bundle.putDouble(field.name, value as Double)
				Boolean::class -> bundle.putBoolean(field.name, value as Boolean)
				Long::class -> bundle.putLong(field.name, value as Long)
				Byte::class -> bundle.putByte(field.name, value as Byte)
				Bundle::class -> bundle.putBundle(field.name, value as Bundle)
				Char::class -> bundle.putChar(field.name, value as Char)
				Short::class -> bundle.putShort(field.name, value as Short)

				Array<String>::class -> bundle.putStringArray(field.name, value as Array<String>)
				BooleanArray::class -> bundle.putBooleanArray(field.name, value as BooleanArray)
				IntArray::class -> bundle.putIntArray(field.name, value as IntArray)
				LongArray::class -> bundle.putLongArray(field.name, value as LongArray)
				FloatArray::class -> bundle.putFloatArray(field.name, value as FloatArray)
				DoubleArray::class -> bundle.putDoubleArray(field.name, value as DoubleArray)
				ByteArray::class -> bundle.putByteArray(field.name, value as ByteArray)
				CharArray::class -> bundle.putCharArray(field.name, value as CharArray)
				ShortArray::class -> bundle.putShortArray(field.name, value as ShortArray)

				else -> {
					if(value is CharSequence) {
						bundle.putCharSequence(field.name, value)
						return
					}

					if(value is Parcelable) {
						bundle.putParcelable(field.name, value)
						return
					}

					if(value is Serializable) {
						bundle.putSerializable(field.name, value)
						return
					}

					if(SafeArgs.debug) {
						Log.e(SafeArgs.TAG, "Unsupported type: ${value::class.java.name}")
					}
				}
			}
		}
	}
}
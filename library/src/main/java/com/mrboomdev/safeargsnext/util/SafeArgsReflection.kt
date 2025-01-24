package com.mrboomdev.safeargsnext.util

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.RestrictTo
import com.mrboomdev.safeargsnext.value.JsonValueWrapper
import com.mrboomdev.safeargsnext.value.SerializableValueWrapper
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.io.Serializable
import java.lang.reflect.Field

@RestrictTo(RestrictTo.Scope.LIBRARY)
object SafeArgsReflection {

	// The irony of the fact that an SafeArgsCreator is using Unsafe
	private val unsafe by lazy {
		return@lazy (Class.forName("sun.misc.Unsafe") as Class<*>)
			.getDeclaredField("theUnsafe")
			.apply { isAccessible = true }
			.get(null)
	}

	private val allocateInstanceMethod by lazy {
		unsafe.javaClass.getDeclaredMethod("allocateInstance", Class::class.java).apply {
			isAccessible = true
		}
	}

	@RestrictTo(RestrictTo.Scope.LIBRARY)
	@Suppress("UNCHECKED_CAST", "DEPRECATION")
	fun <T> readSafeArgs(bundle: Bundle?, clazz: Class<T>): T? {
		if(bundle == null) {
			return null
		}

		val instance = allocateInstanceMethod.invoke(unsafe, clazz) as T

		for(key in bundle.keySet()) {
			val value = bundle.get(key)

			try {
				clazz.getDeclaredField(key).apply {
					isAccessible = true

					if(value is SerializableValueWrapper) {
						set(instance, value.value)
					} else if(value is JsonValueWrapper) {
						set(instance, Json.decodeFromString(Json.serializersModule.serializer(value.clazz), value.json))
					} else if(type == String::class.java && value !is String && value is CharSequence) {
						set(instance, value.toString())
					} else {
						set(instance, value)
					}
				}
			} catch(_: NoSuchFieldError) {}
		}

		return instance
	}

	/**
	 * Special behaviour is required to keep types the same.
	 */
	private fun wrapIfRequired(bundle: Bundle, field: Field, value: Any): Boolean {
		if(value !is Serializable && value !is Parcelable) {
			return false
		}

		if(value is Map<*, *> && field.type != Map::class.java) {
			bundle.putParcelable(field.name, SerializableValueWrapper(value))
			return true
		}

		if(value is List<*> && (
					field.type != List::class.java ||
					field.type != Collection::class.java
		)) {
			bundle.putParcelable(field.name, SerializableValueWrapper(value))
			return true
		}

		if(value is Array<*> && field.type != Array<Serializable>::class.java) {
			bundle.putParcelable(field.name, SerializableValueWrapper(value))
			return true
		}

		return false
	}

	@Suppress("UNCHECKED_CAST")
	@RestrictTo(RestrictTo.Scope.LIBRARY)
	fun writeSafeArgs(bundle: Bundle, safeArgs: Any) {
		for(field in safeArgs.javaClass.declaredFields) {
			field.isAccessible = true
			val value = field.get(safeArgs)

			if(value == null) {
				bundle.remove(field.name)
				continue
			}

			if(wrapIfRequired(bundle, field, value)) continue

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
						continue
					}

					if(value is Parcelable) {
						bundle.putParcelable(field.name, value)
						continue
					}

					if(value is Serializable) {
						bundle.putSerializable(field.name, value)
						continue
					}

					try {
						val json = Json.encodeToString(Json.serializersModule.serializer(field.declaringClass), value)
						bundle.putParcelable(field.name, JsonValueWrapper(field.declaringClass, json))
						continue
					} catch(_: Throwable) {}
					
					if(value is Function<*>) {
						throw UnsupportedOperationException("Function isn't serializable! " +
								"Please, use com.mrboomdev.safeargsnext.value.serializableFunction to fix the problem.")
					}

					throw UnsupportedOperationException("Unsupported type: ${value::class.java.name}")
				}
			}
		}
	}
}
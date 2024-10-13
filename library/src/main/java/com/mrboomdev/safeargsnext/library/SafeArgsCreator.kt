package com.mrboomdev.safeargsnext.library

import android.os.Bundle
import java.lang.reflect.Method

object SafeArgsCreator {
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
	fun <T> createSafeArgs(clazz: Class<T>, bundle: Bundle?): T? {
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
}
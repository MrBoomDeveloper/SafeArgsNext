package com.mrboomdev.safeargsnext.util

import com.mrboomdev.safeargsnext.owner.SafeArgsOwner
import java.util.WeakHashMap

private val weakMap = WeakHashMap<Any, Any>()

@Suppress("UNCHECKED_CAST")
val <T> SafeArgsOwner<T>.rememberSafeArgs: T?
	get() {
		var value = weakMap[this]

		if(value == null) {
			value = safeArgs
			weakMap[this] = value
		}

		return value as? T
	}

fun <T> SafeArgsOwner<T>.forgetSafeArgs() {
	weakMap.remove(this)
}
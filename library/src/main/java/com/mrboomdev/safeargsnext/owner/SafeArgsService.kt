package com.mrboomdev.safeargsnext.owner

import android.content.Intent
import android.os.IBinder
import com.mrboomdev.safeargsnext.util.SafeArgsReflection
import java.util.WeakHashMap

private val weakMap = WeakHashMap<SafeArgsService<*>, Any>()

interface SafeArgsService<T>: SafeArgsOwner<T> {

	@Suppress("UNCHECKED_CAST")
	override val safeArgs: T?
		get() = weakMap[this] as? T

	override val safeArgsOwnerTypeName: String
		get() = SafeArgsService::class.qualifiedName!!

	fun onBind(intent: Intent?): IBinder? {
		val value = if(intent != null) {
			SafeArgsReflection.readSafeArgs(intent.extras, getSafeArgsType())
		} else null

		weakMap[this] = value
		return onBind(value)
	}

	fun onBind(args: T?): IBinder?
}
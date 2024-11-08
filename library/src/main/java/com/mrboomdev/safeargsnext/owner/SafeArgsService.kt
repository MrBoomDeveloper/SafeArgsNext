package com.mrboomdev.safeargsnext.owner

import android.app.Service
import android.content.Intent
import com.mrboomdev.safeargsnext.util.SafeArgsReflection
import java.util.WeakHashMap

private val weakMap = WeakHashMap<SafeArgsService<*>, Any>()

interface SafeArgsService<T>: SafeArgsOwner<T> {

	@Suppress("UNCHECKED_CAST")
	override val safeArgs: T?
		get() = weakMap[this] as? T

	override val safeArgsOwnerTypeName: String
		get() = SafeArgsService::class.qualifiedName!!

	fun onStartCommand(args: T?, flags: Int, startId: Int): Int {
		return Service.START_STICKY
	}

	fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
		val value = SafeArgsReflection.readSafeArgs(intent.extras, getSafeArgsType())
		weakMap[this] = value
		return onStartCommand(value, flags, startId)
	}
}
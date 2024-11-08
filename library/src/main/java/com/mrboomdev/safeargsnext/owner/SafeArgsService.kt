package com.mrboomdev.safeargsnext.owner

import android.app.Service
import android.content.Intent
import com.mrboomdev.safeargsnext.util.SafeArgsReflection

abstract class SafeArgsService<T>: Service(), SafeArgsOwner<T> {
	private var cachedSafeArgs: T? = null

	override val safeArgs: T?
		get() = cachedSafeArgs

	override val safeArgsOwnerTypeName: String
		get() = SafeArgsService::class.qualifiedName!!

	open fun onStartCommand(args: T?, flags: Int, startId: Int): Int {
		return START_STICKY
	}

	override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
		cachedSafeArgs = SafeArgsReflection.readSafeArgs(intent.extras, getSafeArgsType())
		return onStartCommand(cachedSafeArgs, flags, startId)
	}
}
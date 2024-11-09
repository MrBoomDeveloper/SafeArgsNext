package com.mrboomdev.safeargsnext.owner

import android.content.Intent
import com.mrboomdev.safeargsnext.util.SafeArgsReflection
import com.mrboomdev.safeargsnext.util.putSafeArgs

interface SafeArgsActivity<T>: SafeArgsOwner<T> {

	override val safeArgs: T?
		get() = SafeArgsReflection.readSafeArgs(getIntent().extras, getSafeArgsType())

	override val safeArgsOwnerTypeName: String
		get() = SafeArgsActivity::class.qualifiedName!!

	fun putSafeArgs(args: T) {
		setIntent(getIntent().apply {
			this.putSafeArgs(args as Any)
		})
	}

	fun getIntent(): Intent
	fun setIntent(intent: Intent)
}
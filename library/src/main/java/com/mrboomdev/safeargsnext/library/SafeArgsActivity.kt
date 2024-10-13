package com.mrboomdev.safeargsnext.library

import android.content.Intent

interface SafeArgsActivity<T>: SafeArgsOwner<T> {
	override val safeArgs: T?
		get() = SafeArgsCreator.createSafeArgs(getSafeArgsType(), getIntent().extras)

	override val safeArgsOwnerTypeName: String
		get() = SafeArgsActivity::class.qualifiedName!!

	fun getIntent(): Intent
}
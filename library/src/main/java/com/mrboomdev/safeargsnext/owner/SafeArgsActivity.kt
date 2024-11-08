package com.mrboomdev.safeargsnext.owner

import android.content.Intent
import com.mrboomdev.safeargsnext.util.SafeArgsReflection

interface SafeArgsActivity<T>: SafeArgsOwner<T> {

	override val safeArgs: T?
		get() = SafeArgsReflection.readSafeArgs(getIntent().extras, getSafeArgsType())

	override val safeArgsOwnerTypeName: String
		get() = SafeArgsActivity::class.qualifiedName!!

	fun getIntent(): Intent
}
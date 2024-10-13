package com.mrboomdev.safeargsnext.library

import android.os.Bundle

interface SafeArgsFragment<T>: SafeArgsOwner<T> {
	override val safeArgs: T?
		get() = SafeArgsCreator.createSafeArgs(getSafeArgsType(), getArguments())

	override val safeArgsOwnerTypeName: String
		get() = SafeArgsFragment::class.qualifiedName!!

	fun getArguments(): Bundle?
}
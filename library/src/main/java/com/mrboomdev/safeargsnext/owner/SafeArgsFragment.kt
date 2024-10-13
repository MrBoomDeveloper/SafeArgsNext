package com.mrboomdev.safeargsnext.owner

import android.os.Bundle
import com.mrboomdev.safeargsnext.util.SafeArgsReflection

interface SafeArgsFragment<T>: SafeArgsOwner<T> {
	override val safeArgs: T?
		get() = SafeArgsReflection.restoreSafeArgs(getSafeArgsType(), getArguments())

	override val safeArgsOwnerTypeName: String
		get() = SafeArgsFragment::class.qualifiedName!!

	fun getArguments(): Bundle?
}
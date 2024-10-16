package com.mrboomdev.safeargsnext.owner

import android.os.Bundle
import com.mrboomdev.safeargsnext.util.SafeArgsReflection

interface SafeArgsFragment<T>: SafeArgsOwner<T> {
	override val safeArgs: T?
		get() = SafeArgsReflection.readSafeArgs(getArguments(), getSafeArgsType())

	override val safeArgsOwnerTypeName: String
		get() = SafeArgsFragment::class.qualifiedName!!

	fun getArguments(): Bundle?
}
package com.mrboomdev.safeargsnext.owner

import android.os.Bundle
import com.mrboomdev.safeargsnext.util.SafeArgsReflection
import com.mrboomdev.safeargsnext.util.putSafeArgs

interface SafeArgsFragment<T>: SafeArgsOwner<T> {

	override val safeArgs: T?
		get() = SafeArgsReflection.readSafeArgs(getArguments(), getSafeArgsType())

	override val safeArgsOwnerTypeName: String
		get() = SafeArgsFragment::class.qualifiedName!!

	override val safeArgsIsInterface: Boolean
		get() = true

	fun putSafeArgs(args: T?) {
		setArguments(Bundle().also {
			if(args != null) {
				it.putSafeArgs(args as Any)
			}
		})
	}

	fun getArguments(): Bundle?
	fun setArguments(bundle: Bundle?)
}
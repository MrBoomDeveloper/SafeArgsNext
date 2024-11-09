package com.mrboomdev.safeargsnext.owner

import android.os.Bundle
import com.mrboomdev.safeargsnext.util.SafeArgsReflection
import com.mrboomdev.safeargsnext.util.putSafeArgs

interface SafeArgsFragment<T>: SafeArgsOwner<T> {

	override val safeArgs: T?
		get() = SafeArgsReflection.readSafeArgs(getArguments(), getSafeArgsType())

	override val safeArgsOwnerTypeName: String
		get() = SafeArgsFragment::class.qualifiedName!!

	fun putSafeArgs(args: T?) {
		setArguments(Bundle().apply {
			if(args != null) {
				this.putSafeArgs(args as Any)
			}
		})
	}

	fun getArguments(): Bundle?
	fun setArguments(bundle: Bundle?)
}
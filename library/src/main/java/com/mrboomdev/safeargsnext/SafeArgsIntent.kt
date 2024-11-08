package com.mrboomdev.safeargsnext

import android.content.Context
import android.content.Intent
import com.mrboomdev.safeargsnext.owner.SafeArgsOwner
import com.mrboomdev.safeargsnext.util.putSafeArgs
import kotlin.reflect.KClass

class SafeArgsIntent<A>: Intent {

	constructor(
		context: Context,
		clazz: Class<out SafeArgsOwner<A>>
	): super(context, clazz)

	constructor(
		context: Context,
		clazz: Class<out SafeArgsOwner<A>>,
		args: A
	): super(context, clazz) {
		putSafeArgs(args as Any)
	}

	constructor(
		context: Context,
		clazz: KClass<out SafeArgsOwner<A>>
	): super(context, clazz.java)

	constructor(
		context: Context,
		clazz: KClass<out SafeArgsOwner<A>>,
		args: A
	): super(context, clazz.java) {
		putSafeArgs(args as Any)
	}
}
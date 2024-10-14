@file:Suppress("DEPRECATION")

package com.mrboomdev.safeargsnext.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mrboomdev.safeargsnext.owner.SafeArgsFragment
import kotlin.reflect.KClass
import android.app.Fragment as OldFragment

@Suppress("UNCHECKED_CAST")
fun <A> FragmentTransaction.add(
	id: Int,
	clazz: Class<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
): FragmentTransaction {
	return add(id, clazz as Class<out Fragment>,
		if(args != null) Bundle().apply {
			SafeArgsReflection.writeSafeArgs(this, args as Any)
		} else null, tag)
}

fun <A> FragmentTransaction.add(
	id: Int,
	clazz: KClass<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
): FragmentTransaction {
	return add(id, clazz.java, args, tag)
}

@Suppress("UNCHECKED_CAST")
fun <A> FragmentTransaction.add(
	clazz: Class<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
): FragmentTransaction {
	return add(clazz as Class<out Fragment>,
		if(args != null) Bundle().apply {
			SafeArgsReflection.writeSafeArgs(this, args as Any)
		} else null, tag)
}

fun <A> FragmentTransaction.add(
	clazz: KClass<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
): FragmentTransaction {
	return add(clazz.java, args, tag)
}

@Suppress("UNCHECKED_CAST")
fun <A> FragmentTransaction.replace(
	id: Int,
	clazz: Class<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
): FragmentTransaction {
	return replace(id, clazz as Class<out Fragment>,
		if(args != null) Bundle().apply {
			SafeArgsReflection.writeSafeArgs(this, args as Any)
		} else null, tag)
}

fun <A> FragmentTransaction.replace(
	id: Int,
	clazz: KClass<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
): FragmentTransaction {
	return replace(id, clazz.java, args, tag)
}

fun Intent.putSafeArgs(safeArgs: Any) {
	putExtras(Bundle().apply {
		putSafeArgs(safeArgs)
	})
}

fun Bundle.putSafeArgs(safeArgs: Any) {
	SafeArgsReflection.writeSafeArgs(this, safeArgs)
}

inline fun <reified T> Bundle.asSafeArgs(): T? {
	return SafeArgsReflection.readSafeArgs(this, T::class.java)
}

inline fun <reified T> Intent.asSafeArgs(): T? {
	return SafeArgsReflection.readSafeArgs(extras, T::class.java)
}

inline fun <reified T> Activity.getSafeArgs(): T? {
	return SafeArgsReflection.readSafeArgs(intent.extras, T::class.java)
}

inline fun <reified T> Fragment.getSafeArgs(): T? {
	return SafeArgsReflection.readSafeArgs(arguments, T::class.java)
}

inline fun <reified T> OldFragment.getSafeArgs(): T? {
	return SafeArgsReflection.readSafeArgs(arguments, T::class.java)
}
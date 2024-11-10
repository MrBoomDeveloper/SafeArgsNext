package com.mrboomdev.safeargsnext.util

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mrboomdev.safeargsnext.owner.SafeArgsFragment
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
fun <A> FragmentTransaction.add(
	id: Int,
	clazz: Class<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
) = add(id, clazz as Class<out Fragment>, args?.let {
	Bundle().apply { SafeArgsReflection.writeSafeArgs(this, args as Any) }
}, tag)

fun <A> FragmentTransaction.add(
	id: Int,
	clazz: KClass<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
) = add(id, clazz.java, args, tag)

@Suppress("UNCHECKED_CAST")
fun <A> FragmentTransaction.add(
	clazz: Class<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
) = add(clazz as Class<out Fragment>, args?.let {
	Bundle().apply { SafeArgsReflection.writeSafeArgs(this, args as Any) }
}, tag)

fun <A> FragmentTransaction.add(
	clazz: KClass<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
) = add(clazz.java, args, tag)

@Suppress("UNCHECKED_CAST")
fun <A> FragmentTransaction.replace(
	id: Int,
	clazz: Class<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
) = replace(id, clazz as Class<out Fragment>, args?.let {
	Bundle().apply { SafeArgsReflection.writeSafeArgs(this, args as Any) }
}, tag)

fun <A> FragmentTransaction.replace(
	id: Int,
	clazz: KClass<out SafeArgsFragment<A>>,
	args: A? = null,
	tag: String? = null
) = replace(id, clazz.java, args, tag)

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
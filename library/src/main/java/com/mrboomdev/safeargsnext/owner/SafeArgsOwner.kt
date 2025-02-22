package com.mrboomdev.safeargsnext.owner

import android.os.Build
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

interface SafeArgsOwner<T> {
	val safeArgsOwnerTypeName: String
	val safeArgsIsInterface: Boolean
	val safeArgs: T?

	private fun getArgType(clazz: Class<*>): Type? {
		return if(safeArgsIsInterface) clazz.genericInterfaces.find {
			if(it !is ParameterizedType) {
				// We can't get args type without knowing generic types.
				return@find false
			}

			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
				it.typeName.startsWith("$safeArgsOwnerTypeName<")
			} else {
				it.toString().startsWith("$safeArgsOwnerTypeName<")
			}
		} else clazz.genericSuperclass?.let {
			if(it !is ParameterizedType) {
				// We can't get args type without knowing generic types.
				return null
			}

			val matches = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
				it.typeName.startsWith("$safeArgsOwnerTypeName<")
			} else {
				it.toString().startsWith("$safeArgsOwnerTypeName<")
			}

			return if(matches) it else null
		}
	}

	@Suppress("UNCHECKED_CAST")
	fun getSafeArgsType(): Class<T> {
		var lastClazz: Class<*> = javaClass
		var type: Type?

		while(true) {
			type = getArgType(lastClazz)

			if(type == null) {
				lastClazz = lastClazz.superclass
			} else {
				break
			}

			if(lastClazz == Object::class.java || lastClazz == Any::class.java) {
				break
			}
		}

		if(type == null) {
			throw UnsupportedOperationException("Failed to get arguments type! Please, report library dev about that situation with attached code.")
		}

		return (type as ParameterizedType).actualTypeArguments[0] as Class<T>
	}
}
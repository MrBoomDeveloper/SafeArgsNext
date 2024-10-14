package com.mrboomdev.safeargsnext

class CustomList<E>: ArrayList<E> {
	constructor(vararg args: E): this(args.asList())
	constructor(original: Collection<E>): super(original)
	constructor(): super()
}
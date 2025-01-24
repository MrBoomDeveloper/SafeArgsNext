package com.mrboomdev.safeargsnext.value

import java.io.Serializable

/**
 * You may ran into the following problem:
 * ```
 * Overload resolution ambiguity. All these functions match.
 * ```
 * To fix it, you'll have add an arrow without any arguments, and then everything will work!
 * ```
 * val lambda = serializableFunction { ->
 * 	println("Hello, from an serialized function!")
 * }
 * ```
 */
inline fun <T> serializableFunction(
    crossinline function: () -> T
): () -> T = object : () -> T, Serializable {
    override fun invoke(): T = function()
}

inline fun <T, A1> serializableFunction(
    crossinline function: (A1) -> T
): (A1) -> T = object : (A1) -> T, Serializable {
    override fun invoke(arg1: A1): T = function(arg1)
}

inline fun <T, A1, A2> serializableFunction(
    crossinline function: (A1, A2) -> T
): (A1, A2) -> T = object : (A1, A2) -> T, Serializable {
    override fun invoke(arg1: A1, arg2: A2): T = function(arg1, arg2)
}

inline fun <T, A1, A2, A3> serializableFunction(
    crossinline function: (A1, A2, A3) -> T
): (A1, A2, A3) -> T = object : (A1, A2, A3) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3
    ): T = function(arg1, arg2, arg3)
}

inline fun <T, A1, A2, A3, A4> serializableFunction(
    crossinline function: (A1, A2, A3, A4) -> T
): (A1, A2, A3, A4) -> T = object : (A1, A2, A3, A4) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4
    ): T = function(arg1, arg2, arg3, arg4)
}

inline fun <T, A1, A2, A3, A4, A5> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5) -> T
): (A1, A2, A3, A4, A5) -> T = object : (A1, A2, A3, A4, A5) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5
    ): T = function(arg1, arg2, arg3, arg4, arg5)
}

inline fun <T, A1, A2, A3, A4, A5, A6> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6) -> T
): (A1, A2, A3, A4, A5, A6) -> T = object : (A1, A2, A3, A4, A5, A6) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7) -> T
): (A1, A2, A3, A4, A5, A6, A7) -> T = object : (A1, A2, A3, A4, A5, A6, A7) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8) -> T = object : (A1, A2, A3, A4, A5, A6, A7, A8) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9) -> T = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) -> T = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) -> T = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10, arg11: A11
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, 
        arg11)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) -> T 
        = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10, 
        arg11: A11, arg12: A12
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, 
        arg11, arg12)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) -> T
        = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10,
        arg11: A11, arg12: A12, arg13: A13
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, 
        arg11, arg12, arg13)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) -> T
        = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10,
        arg11: A11, arg12: A12, arg13: A13, arg14: A14
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, 
        arg11, arg12, arg13, arg14)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) -> T
        = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10,
        arg11: A11, arg12: A12, arg13: A13, arg14: A14, arg15: A15
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, 
        arg11, arg12, arg13, arg14, arg15)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) -> T
        = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10,
        arg11: A11, arg12: A12, arg13: A13, arg14: A14, arg15: A15, arg16: A16
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, 
        arg11, arg12, arg13, arg14, arg15, arg16)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) -> T
        = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10,
        arg11: A11, arg12: A12, arg13: A13, arg14: A14, arg15: A15, arg16: A16, arg17: A17
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, 
        arg11, arg12, arg13, arg14, arg15, arg16, arg17)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) -> T
        = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10,
        arg11: A11, arg12: A12, arg13: A13, arg14: A14, arg15: A15, arg16: A16, arg17: A17, arg18: A18
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, 
        arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) -> T
        = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10,
        arg11: A11, arg12: A12, arg13: A13, arg14: A14, arg15: A15, arg16: A16, arg17: A17, arg18: A18, arg19: A19
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10,
        arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) -> T
        = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10,
        arg11: A11, arg12: A12, arg13: A13, arg14: A14, arg15: A15, arg16: A16, arg17: A17, arg18: A18, arg19: A19, arg20: A20
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10,
        arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, 
        A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, 
                           A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, 
    A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) -> T
        = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, 
                    A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10,
        arg11: A11, arg12: A12, arg13: A13, arg14: A14, arg15: A15, arg16: A16, arg17: A17, arg18: A18, arg19: A19, arg20: A20, 
        arg21: A21
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10,
        arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, 
        arg21)
}

inline fun <T, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10,
        A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22> serializableFunction(
    crossinline function: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10,
                           A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) -> T
): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10,
    A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) -> T
        = object : (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10,
                    A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22) -> T, Serializable {
    override fun invoke(
        arg1: A1, arg2: A2, arg3: A3, arg4: A4, arg5: A5, arg6: A6, arg7: A7, arg8: A8, arg9: A9, arg10: A10,
        arg11: A11, arg12: A12, arg13: A13, arg14: A14, arg15: A15, arg16: A16, arg17: A17, arg18: A18, arg19: A19, arg20: A20,
        arg21: A21, arg22: A22
    ): T = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10,
        arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20,
        arg21, arg22)
}

inline fun <T> serializableFunction0(crossinline function: () -> T): () -> T = serializableFunction(function)
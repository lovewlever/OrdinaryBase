package com.ordinary.basis.entities

sealed class ResultSealed<out T> {
    data class Success<out T>(val value: T): ResultSealed<T>()
    data class Failure(val code: Int, val msg: String): ResultSealed<Nothing>()
    object Loading : ResultSealed<Nothing>()
}
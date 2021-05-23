package com.graphq_test.data.data

sealed class AppResult<T> {
    class Success<T>(val data: T) : AppResult<T>()
    class Failure<T>(val errorMessage: String) : AppResult<T>()
}
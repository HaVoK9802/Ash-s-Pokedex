package com.example.ashspokedex.utils

sealed class RequestStatus<T> {

    data class Success<T>(val data:T): RequestStatus<T>()
    data class Error<T>(val message: String = ""): RequestStatus<T>()
    class Loading<T> : RequestStatus<T>()

}
package com.knvovk.gamehub.presentation

sealed class State<T>(val data: T? = null, val msg: String? = null) {
    class Success<T>(data: T) : State<T>(data = data)
    class Loading<T> : State<T>()
    class Failure<T>(msg: String) : State<T>(msg = msg)
}
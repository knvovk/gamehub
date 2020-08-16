package com.knvovk.gamehub.presentation

sealed class NetworkState {
    object Loading : NetworkState()
    object Success : NetworkState()
    object Failure : NetworkState()
}
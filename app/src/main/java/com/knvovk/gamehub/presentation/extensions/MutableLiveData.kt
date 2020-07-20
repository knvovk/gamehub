package com.knvovk.gamehub.presentation.extensions

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.default(defaultValue: T) = apply { value = defaultValue }
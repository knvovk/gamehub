package com.knvovk.gamehub.presentation.extensions

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.showIf(predicate: Boolean) {
    visibility = if (predicate) View.VISIBLE else View.GONE
}
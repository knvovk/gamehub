package com.knvovk.gamehub.data.mappers

interface Mapper<R, M> {

    fun map(response: R): M
}
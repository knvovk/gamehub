package com.knvovk.gamehub.data.mappers

interface Mapper<D, N, M> {

    fun fromNet(netModel: N): M

    fun fromDb(dbModel: D): M
}
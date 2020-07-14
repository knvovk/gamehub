package com.knvovk.gamehub.data.mappers

interface Mapper<D, N, M> {

    fun mapNetModel(netModel: N): M

    fun mapDbModel(dbModel: D): M
}
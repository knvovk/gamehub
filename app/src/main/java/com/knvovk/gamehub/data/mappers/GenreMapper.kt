package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.models.genre.Genre
import com.knvovk.gamehub.domain.models.genre.GenreDb
import com.knvovk.gamehub.domain.models.genre.GenreNet

class GenreMapper : Mapper<GenreDb, GenreNet, Genre> {

    // TODO: Implement it
    override fun mapDbModel(dbModel: GenreDb): Genre {
        return Genre.of(id = dbModel.id)
    }

    override fun mapNetModel(netModel: GenreNet): Genre {
        return Genre.of(id = netModel.id)
    }
}
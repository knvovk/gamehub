package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.genre.Genre
import com.knvovk.gamehub.domain.genre.GenreResponse

class GenreMapper : Mapper<GenreResponse, Genre> {

    override fun map(response: GenreResponse) = Genre.of(id = response.id)
}
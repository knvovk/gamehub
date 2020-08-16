package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.game.Game
import com.knvovk.gamehub.domain.game.GameResponse

class GameMapper : Mapper<GameResponse, Game> {

    override fun map(response: GameResponse) = Game(
        id = response.id,
        genres = response.genres.map { GenreMapper().map(it) },
        image = response.background_image,
        metacritic = response.metacritic,
        name = response.name,
        platforms = response.platforms.map { PlatformMapper().map(it) },
        released = LocalDateMapper.map(response.released),
        tba = response.tba
    )
}
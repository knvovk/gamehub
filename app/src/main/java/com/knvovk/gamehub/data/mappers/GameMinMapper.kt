package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.models.gamemin.Game
import com.knvovk.gamehub.domain.models.gamemin.GameDb
import com.knvovk.gamehub.domain.models.gamemin.GameNet

class GameMinMapper : Mapper<GameDb, GameNet, Game> {

    // TODO: Implement it
    override fun fromDb(dbModel: GameDb): Game {
        return Game(
            id = dbModel.id,
            background_image = dbModel.background_image,
            name = dbModel.name,
            released = LocalDateMapper().map(dbModel.released),
            tba = dbModel.tba,
            metacritic = dbModel.metacritic,
            platforms = dbModel.platforms
                .map { PlatformMapper().fromDb(it) },
            genres = dbModel.genres.map {
                GenreMapper().fromDb(it)
            }
        )
    }

    override fun fromNet(netModel: GameNet): Game {
        return Game(
            id = netModel.id,
            background_image = netModel.background_image,
            name = netModel.name,
            released = LocalDateMapper().map(netModel.released),
            tba = netModel.tba,
            metacritic = netModel.metacritic,
            platforms = netModel.platforms
                .map { PlatformDetailsMapper().fromNet(it) }
                .map { it.platform },
            genres = netModel.genres.map {
                GenreMapper().fromNet(it)
            }
        )
    }
}
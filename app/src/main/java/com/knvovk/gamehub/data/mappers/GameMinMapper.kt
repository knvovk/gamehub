package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.models.gamemin.GameMin
import com.knvovk.gamehub.domain.models.gamemin.GameMinDb
import com.knvovk.gamehub.domain.models.gamemin.GameMinNet

class GameMinMapper : Mapper<GameMinDb, GameMinNet, GameMin> {

    // TODO: Implement it
    override fun fromDb(dbModel: GameMinDb): GameMin {
        return GameMin(
            id = dbModel.id,
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

    override fun fromNet(netModel: GameMinNet): GameMin {
        return GameMin(
            id = netModel.id,
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
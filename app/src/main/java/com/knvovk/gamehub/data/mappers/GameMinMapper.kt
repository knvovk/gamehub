package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.models.gamemin.GameMin
import com.knvovk.gamehub.domain.models.gamemin.GameMinDb
import com.knvovk.gamehub.domain.models.gamemin.GameMinNet

class GameMinMapper : Mapper<GameMinDb, GameMinNet, GameMin> {

    // TODO: Implement it
    override fun mapDbModel(dbModel: GameMinDb): GameMin {
        return GameMin(
            id = dbModel.id,
            name = dbModel.name,
            released = LocalDateMapper().map(dbModel.released),
            tba = dbModel.tba,
            metacritic = dbModel.metacritic,
            playtime = dbModel.playtime,
            platforms = dbModel.platforms.map {
                PlatformDetailsMapper().mapDbModel(it)
            },
            genres = dbModel.genres.map {
                GenreMapper().mapDbModel(it)
            }
        )
    }

    override fun mapNetModel(netModel: GameMinNet): GameMin {
        return GameMin(
            id = netModel.id,
            name = netModel.name,
            released = LocalDateMapper().map(netModel.released),
            tba = netModel.tba,
            metacritic = netModel.metacritic,
            playtime = netModel.playtime,
            platforms = netModel.platforms.map {
                PlatformDetailsMapper().mapNetModel(it)
            },
            genres = netModel.genres.map {
                GenreMapper().mapNetModel(it)
            }
        )
    }
}
package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.models.gamespage.GamesPage
import com.knvovk.gamehub.domain.models.gamespage.GamesPageDb
import com.knvovk.gamehub.domain.models.gamespage.GamesPageNet

class GamesPageMapper : Mapper<GamesPageDb, GamesPageNet, GamesPage> {

    // TODO: Implement it
    override fun fromDb(dbModel: GamesPageDb): GamesPage {
        return GamesPage(
            games = dbModel.games.map {
                GameMinMapper().fromDb(it)
            }
        )
    }

    override fun fromNet(netModel: GamesPageNet): GamesPage {
        return GamesPage(
            games = netModel.results.map {
                GameMinMapper().fromNet(it)
            }
        )
    }
}
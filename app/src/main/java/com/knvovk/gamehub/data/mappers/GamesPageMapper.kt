package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.data.NONE
import com.knvovk.gamehub.domain.models.gamespage.GamesPage
import com.knvovk.gamehub.domain.models.gamespage.GamesPageDb
import com.knvovk.gamehub.domain.models.gamespage.GamesPageNet

class GamesPageMapper : Mapper<GamesPageDb, GamesPageNet, GamesPage> {

    // TODO: Implement it
    override fun fromDb(dbModel: GamesPageDb): GamesPage {
        return GamesPage(
            next = dbModel.next,
            previous = dbModel.previous ?: NONE,
            games = dbModel.results.map {
                GameMinMapper().fromDb(it)
            }
        )
    }

    override fun fromNet(netModel: GamesPageNet): GamesPage {
        return GamesPage(
            next = netModel.next,
            previous = netModel.previous ?: NONE,
            games = netModel.results.map {
                GameMinMapper().fromNet(it)
            }
        )
    }
}
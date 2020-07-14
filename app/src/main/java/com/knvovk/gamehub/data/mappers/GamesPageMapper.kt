package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.data.UNKNOWN
import com.knvovk.gamehub.domain.models.gamespage.GamesPage
import com.knvovk.gamehub.domain.models.gamespage.GamesPageDb
import com.knvovk.gamehub.domain.models.gamespage.GamesPageNet

class GamesPageMapper : Mapper<GamesPageDb, GamesPageNet, GamesPage> {

    // TODO: Implement it
    override fun mapDbModel(dbModel: GamesPageDb): GamesPage {
        return GamesPage(
            next = dbModel.next,
            previous = dbModel.previous ?: UNKNOWN,
            games = dbModel.results.map {
                GameMinMapper().mapDbModel(it)
            }
        )
    }

    override fun mapNetModel(netModel: GamesPageNet): GamesPage {
        return GamesPage(
            next = netModel.next,
            previous = netModel.previous ?: UNKNOWN,
            games = netModel.results.map {
                GameMinMapper().mapNetModel(it)
            }
        )
    }
}
package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.gamespage.GamesPage
import com.knvovk.gamehub.domain.gamespage.GamesPageResponse

class GamesPageMapper : Mapper<GamesPageResponse, GamesPage> {

    override fun map(response: GamesPageResponse) = GamesPage(
        list = response.results
            .map { GameMapper().map(it) }
    )
}
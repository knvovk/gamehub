package com.knvovk.gamehub.domain.repositories

import com.knvovk.gamehub.domain.models.gamespage.GamesPage
import io.reactivex.rxjava3.core.Single

interface GamesPageRepository {

    fun fetchTrendingGames(): Single<GamesPage>
}
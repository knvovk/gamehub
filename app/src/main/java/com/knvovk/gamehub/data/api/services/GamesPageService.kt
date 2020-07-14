package com.knvovk.gamehub.data.api.services

import com.knvovk.gamehub.domain.models.gamespage.GamesPageNet
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesPageService {

    @GET(GAMES)
    fun getGamesByDate(
        @Query(DATES) release: String,
        @Query(ORDERING) ordering: String = RATING_DESC
    ): Single<GamesPageNet>
}
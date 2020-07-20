package com.knvovk.gamehub.data.api.services

import com.knvovk.gamehub.domain.models.gamespage.GamesPageNet
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GameService {

    @GET(GAMES)
    fun getGamesByReleaseDate(
        @Query(DATES) release: String,
        @Query(ORDERING) ordering: String = RATING_DESC,
        @Query(PAGE) page: Int = 1
    ): Observable<GamesPageNet>
}
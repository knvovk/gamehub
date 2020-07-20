package com.knvovk.gamehub.domain.repositories

import com.knvovk.gamehub.domain.models.gamemin.GameMin
import io.reactivex.rxjava3.core.Observable

interface GameRepository {

    fun fetchTrendingGames(): Observable<List<GameMin>>
}
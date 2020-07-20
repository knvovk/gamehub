package com.knvovk.gamehub.data.implementations

import com.knvovk.gamehub.data.api.services.GameService
import com.knvovk.gamehub.data.mappers.GamesPageMapper
import com.knvovk.gamehub.domain.models.gamemin.GameMin
import com.knvovk.gamehub.domain.repositories.GameRepository
import io.reactivex.rxjava3.core.Observable
import java.time.LocalDate

class GameRepositoryImpl(
    private val service: GameService,
    private val mapper: GamesPageMapper
) : GameRepository {

    override fun fetchTrendingGames(): Observable<List<GameMin>> {
        return fetchTrendingGamesFromNet()
    }

    private fun fetchTrendingGamesFromNet(): Observable<List<GameMin>> {
        val year = LocalDate.now().year
        return service.getGamesByReleaseDate("$year-01-01,$year-12-31")
            .map { mapper.fromNet(it) }
            .map { it.games }
    }
}
package com.knvovk.gamehub.data.implementations

import com.knvovk.gamehub.data.api.services.GamesPageService
import com.knvovk.gamehub.data.mappers.GamesPageMapper
import com.knvovk.gamehub.domain.models.gamespage.GamesPage
import com.knvovk.gamehub.domain.repositories.GamesPageRepository
import io.reactivex.rxjava3.core.Single
import org.joda.time.LocalDate

class GamesPageRepositoryImpl(
    private val service: GamesPageService,
    private val mapper: GamesPageMapper
) : GamesPageRepository {

    override fun fetchTrendingGames(): Single<GamesPage> {
        val year = LocalDate().year
        return service.getGamesByDate(release = "$year-01-01,$year-12-31")
            .map { mapper.mapNetModel(it) }
    }
}
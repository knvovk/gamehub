package com.knvovk.gamehub.domain.gamespage

import com.knvovk.gamehub.domain.game.GameResponse

data class GamesPageResponse(
    val next: String?,
    val previous: String?,
    val results: List<GameResponse>
)
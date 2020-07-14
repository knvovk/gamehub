package com.knvovk.gamehub.domain.models.gamespage

import com.knvovk.gamehub.domain.models.gamemin.GameMinDb

data class GamesPageDb(
    val next: String,
    val previous: String?,
    val results: List<GameMinDb>
)
package com.knvovk.gamehub.domain.models.gamespage

import com.knvovk.gamehub.domain.models.gamemin.GameMin

data class GamesPage(
    val next: String,
    val previous: String,
    val games: List<GameMin>
)
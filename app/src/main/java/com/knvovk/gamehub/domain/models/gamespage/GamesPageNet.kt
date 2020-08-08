package com.knvovk.gamehub.domain.models.gamespage

import com.knvovk.gamehub.domain.models.gamemin.GameMinNet

data class GamesPageNet(
    val next: String?,
    val previous: String?,
    val results: List<GameMinNet>
)
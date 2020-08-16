package com.knvovk.gamehub.domain.game

import com.knvovk.gamehub.domain.genre.Genre
import com.knvovk.gamehub.domain.platform.Platform
import java.time.LocalDate

data class Game(
    val id: Int,
    val genres: List<Genre>,
    val image: String?,
    val metacritic: Int,
    val name: String,
    val platforms: List<Platform>,
    val released: LocalDate,
    val tba: Boolean
)
package com.knvovk.gamehub.domain.models.gamemin

import com.knvovk.gamehub.domain.models.genre.Genre
import com.knvovk.gamehub.domain.models.platform.Platform
import java.time.LocalDate

data class Game(
    val id: Int,
    val background_image: String?,
    val genres: List<Genre>,
    val metacritic: Int,
    val name: String,
    val platforms: List<Platform>,
    val released: LocalDate,
    val tba: Boolean
)
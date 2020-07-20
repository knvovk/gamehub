package com.knvovk.gamehub.domain.models.gamemin

import com.knvovk.gamehub.domain.models.genre.Genre
import com.knvovk.gamehub.domain.models.platform.Platform
import java.time.LocalDate

data class GameMin(
    val id: Int,
    val name: String,
    val released: LocalDate,
    val tba: Boolean,
    val metacritic: Int,
    val platforms: List<Platform>,
    val genres: List<Genre>
)
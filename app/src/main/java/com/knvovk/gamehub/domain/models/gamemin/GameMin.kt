package com.knvovk.gamehub.domain.models.gamemin

import com.knvovk.gamehub.domain.models.genre.Genre
import com.knvovk.gamehub.domain.models.platformdetails.PlatformDetails
import org.joda.time.LocalDate

data class GameMin(
    val id: Int,
    val name: String,
    val released: LocalDate,
    val tba: Boolean,
    val metacritic: Int,
    val playtime: Int,
    val platforms: List<PlatformDetails>,
    val genres: List<Genre>
)
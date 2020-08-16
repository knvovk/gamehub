package com.knvovk.gamehub.domain.game

import com.knvovk.gamehub.domain.genre.GenreResponse
import com.knvovk.gamehub.domain.platform.PlatformDetailsResponse

data class GameResponse(
    val id: Int,
    val background_image: String?,
    val genres: List<GenreResponse>,
    val metacritic: Int,
    val name: String,
    val platforms: List<PlatformDetailsResponse>,
    val released: String,
    val tba: Boolean
)
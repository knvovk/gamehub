package com.knvovk.gamehub.domain.models.gamemin

import com.knvovk.gamehub.domain.models.genre.GenreDb
import com.knvovk.gamehub.domain.models.platform.PlatformDb

data class GameDb(
    val id: Int,
    val background_image: String?,
    val name: String,
    val released: String,
    val tba: Boolean,
    val metacritic: Int,
    val platforms: List<PlatformDb>,
    val genres: List<GenreDb>
)
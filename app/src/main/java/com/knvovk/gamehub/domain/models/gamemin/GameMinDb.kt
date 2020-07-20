package com.knvovk.gamehub.domain.models.gamemin

import com.knvovk.gamehub.domain.models.genre.GenreDb
import com.knvovk.gamehub.domain.models.platform.PlatformDb

data class GameMinDb(
    val id: Int,
    val name: String,
    val released: String,
    val tba: Boolean,
    val metacritic: Int,
    val platforms: List<PlatformDb>,
    val genres: List<GenreDb>
)
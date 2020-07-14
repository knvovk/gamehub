package com.knvovk.gamehub.domain.models.gamemin

import com.knvovk.gamehub.domain.models.genre.GenreDb
import com.knvovk.gamehub.domain.models.platformdetails.PlatformDetailsDb

data class GameMinDb(
    val id: Int,
    val name: String,
    val released: String,
    val tba: Boolean,
    val metacritic: Int,
    val playtime: Int,
    val platforms: List<PlatformDetailsDb>,
    val genres: List<GenreDb>
)
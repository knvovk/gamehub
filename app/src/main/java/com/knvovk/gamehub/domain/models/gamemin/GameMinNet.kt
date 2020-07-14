package com.knvovk.gamehub.domain.models.gamemin

import com.knvovk.gamehub.domain.models.genre.GenreNet
import com.knvovk.gamehub.domain.models.platformdetails.PlatformDetailsNet

data class GameMinNet(
    val id: Int,
    val name: String,
    val released: String,
    val tba: Boolean,
    val metacritic: Int,
    val playtime: Int,
    val platforms: List<PlatformDetailsNet>,
    val genres: List<GenreNet>
)
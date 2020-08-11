package com.knvovk.gamehub.domain.models.gamemin

import com.knvovk.gamehub.domain.models.genre.GenreNet
import com.knvovk.gamehub.domain.models.platformdetails.PlatformDetailsNet

data class GameNet(
    val id: Int,
    val background_image: String?,
    val genres: List<GenreNet>,
    val metacritic: Int,
    val name: String,
    val platforms: List<PlatformDetailsNet>,
    val released: String,
    val tba: Boolean
)
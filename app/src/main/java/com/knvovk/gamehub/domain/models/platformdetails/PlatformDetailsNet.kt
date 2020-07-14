package com.knvovk.gamehub.domain.models.platformdetails

import com.knvovk.gamehub.domain.models.platform.PlatformNet
import com.knvovk.gamehub.domain.models.requirements.RequirementsNet

data class PlatformDetailsNet(
    val platform: PlatformNet,
    val released_at: String?,
    val requirements_en: RequirementsNet?
)
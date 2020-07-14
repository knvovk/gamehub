package com.knvovk.gamehub.domain.models.platformdetails

import com.knvovk.gamehub.domain.models.platform.PlatformDb
import com.knvovk.gamehub.domain.models.requirements.RequirementsDb

data class PlatformDetailsDb(
    val platform: PlatformDb,
    val released_at: String?,
    val requirements_en: RequirementsDb?
)
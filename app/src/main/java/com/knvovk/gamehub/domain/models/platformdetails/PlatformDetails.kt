package com.knvovk.gamehub.domain.models.platformdetails

import com.knvovk.gamehub.domain.models.platform.Platform
import com.knvovk.gamehub.domain.models.requirements.Requirements
import java.time.LocalDate

data class PlatformDetails(
    val platform: Platform,
    val releasedAt: LocalDate,
    val requirements: Requirements
)
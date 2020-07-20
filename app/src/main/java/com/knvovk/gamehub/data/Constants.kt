package com.knvovk.gamehub.data

import com.knvovk.gamehub.domain.models.requirements.Requirements
import java.time.LocalDate

const val NONE = ""
val DATE_NONE: LocalDate = LocalDate.of(0, 1, 1)
val REQUIREMENTS_NONE = Requirements(minimum = NONE, recommended = NONE)
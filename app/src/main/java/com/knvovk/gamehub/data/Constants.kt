package com.knvovk.gamehub.data

import com.knvovk.gamehub.domain.models.requirements.Requirements
import org.joda.time.LocalDate

const val BASE_URL = "https://api.rawg.io/api/"
const val USER_AGENT = "GameHub Mobile App"
const val UNKNOWN = "UNKNOWN"

val DATE_UNKNOWN = LocalDate(0, 1, 1)
val REQUIREMENTS_UNKNOWN = Requirements(minimum = UNKNOWN, recommended = UNKNOWN)
package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.data.DATE_UNKNOWN
import org.joda.time.LocalDate

class LocalDateMapper {

    fun map(date: String?): LocalDate {
        return date?.let { LocalDate.parse(it) } ?: DATE_UNKNOWN
    }
}
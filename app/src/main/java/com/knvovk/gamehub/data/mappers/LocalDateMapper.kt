package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.data.DATE_NONE
import java.time.LocalDate

class LocalDateMapper {

    fun map(date: String?): LocalDate {
        return date?.let { LocalDate.parse(it) } ?: DATE_NONE
    }
}
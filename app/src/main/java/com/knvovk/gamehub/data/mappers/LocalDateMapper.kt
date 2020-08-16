package com.knvovk.gamehub.data.mappers

import java.time.LocalDate

class LocalDateMapper {

    companion object {
        private val DATE_NONE: LocalDate = LocalDate.of(0, 1, 1)

        fun map(date: String?): LocalDate {
            return date?.let { LocalDate.parse(it) } ?: DATE_NONE
        }
    }
}
package com.knvovk.gamehub.data.mappers

import java.time.LocalDate

class LocalDateMapper {

    companion object {
        fun map(date: String?): LocalDate {
            return date?.let { LocalDate.parse(it) } ?: LocalDate.of(0, 1, 1)
        }
    }
}
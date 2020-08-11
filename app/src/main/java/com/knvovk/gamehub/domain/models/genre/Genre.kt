package com.knvovk.gamehub.domain.models.genre

enum class Genre(val id: Int, val _name: String) {
    RACING(id = 1, _name = "Гонки"),
    SHOOTER(id = 2, _name = "Шутер"),
    ADVENTURE(id = 3, _name = "Приключения"),
    ACTION(id = 4, _name = "Экшен"),
    RPG(id = 5, _name = "Ролевая"),
    FIGHTING(id = 6, _name = "Файтинг"),
    PUZZLE(id = 7, _name = "Пазл"),
    STRATEGY(id = 10, _name = "Стратегия"),
    ARCADE(id = 11, _name = "Аркадная"),
    SIMULATION(id = 14, _name = "Симулятор"),
    SPORTS(id = 15, _name = "Спортивная"),
    CARD(id = 17, _name = "Карточная"),
    FAMILY(id = 19, _name = "Семейная"),
    BOARD_GAMES(id = 28, _name = "Настольная"),
    EDUCATIONAL(id = 34, _name = "Образовательная"),
    CASUAL(id = 40, _name = "Казуальная"),
    INDIE(id = 51, _name = "Инди"),
    MASSIVELY_MULTIPLAYER(id = 59, _name = "ММО"),
    PLATFORMER(id = 83, _name = "Платформер"),
    NONE(id = -1, _name = "");

    companion object {
        fun of(id: Int): Genre = values().firstOrNull { it.id == id } ?: NONE
    }
}
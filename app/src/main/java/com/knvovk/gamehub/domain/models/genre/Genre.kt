package com.knvovk.gamehub.domain.models.genre

enum class Genre(val id: Int, val name_: String) {
    RACING(id = 1, name_ = "Racing"),
    SHOOTER(id = 2, name_ = "Shooter"),
    ADVENTURE(id = 3, name_ = "Adventure"),
    ACTION(id = 4, name_ = "Action"),
    RPG(id = 5, name_ = "RPG"),
    FIGHTING(id = 6, name_ = "Fighting"),
    PUZZLE(id = 7, name_ = "Puzzle"),
    STRATEGY(id = 10, name_ = "Strategy"),
    ARCADE(id = 11, name_ = "Arcade"),
    SIMULATION(id = 14, name_ = "Simulation"),
    SPORTS(id = 15, name_ = "Sports"),
    CARD(id = 17, name_ = "Card"),
    FAMILY(id = 19, name_ = "Family"),
    BOARD_GAMES(id = 28, name_ = "Board Games"),
    EDUCATIONAL(id = 34, name_ = "Educational"),
    CASUAL(id = 40, name_ = "Casual"),
    INDIE(id = 51, name_ = "Indie"),
    MASSIVELY_MULTILAYER(id = 59, name_ = "Massively Multiplayer"),
    PLATFORMER(id = 83, name_ = "Platformer"),
    UNKNOWN(id = -1, name_ = "Unknown");

    companion object {
        fun of(id: Int): Genre = values().firstOrNull { it.id == id } ?: UNKNOWN
    }
}

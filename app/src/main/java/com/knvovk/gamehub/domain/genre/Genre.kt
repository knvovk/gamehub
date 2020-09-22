package com.knvovk.gamehub.domain.genre

enum class Genre(val id: Int, val _name: String) {
    RACING(id = 1, _name = "Racing"),
    SHOOTER(id = 2, _name = "Shooter"),
    ADVENTURE(id = 3, _name = "Adventure"),
    ACTION(id = 4, _name = "Action"),
    RPG(id = 5, _name = "RPG"),
    FIGHTING(id = 6, _name = "Fighting"),
    PUZZLE(id = 7, _name = "Puzzle"),
    STRATEGY(id = 10, _name = "Strategy"),
    ARCADE(id = 11, _name = "Arcade"),
    SIMULATION(id = 14, _name = "Simulation"),
    SPORTS(id = 15, _name = "Sports"),
    CARD(id = 17, _name = "Card"),
    FAMILY(id = 19, _name = "Family"),
    BOARD_GAMES(id = 28, _name = "Board games"),
    EDUCATIONAL(id = 34, _name = "Educational"),
    CASUAL(id = 40, _name = "Casual"),
    INDIE(id = 51, _name = "Indie"),
    MASSIVELY_MULTIPLAYER(id = 59, _name = "ММО"),
    PLATFORMER(id = 83, _name = "Platformer"),
    NONE(id = -1, _name = "");

    companion object {
        fun of(id: Int): Genre = values().firstOrNull { it.id == id } ?: NONE
    }
}
package com.arasaka.tictactoe

enum class GameState {
    NOT_STARTED,
    STARTED,
    FINISHED
}

enum class PlayerId {
    PLAYER_1,
    PLAYER_2;

    override fun toString(): String {
        return if (this == PLAYER_1) {
            "Player 1"
        } else {
            "Player 2"
        }
    }
}

enum class Weapon {
    X,
    O
}

enum class GameBoardValue {
    NO_VALUE,
    X,
    O
}

data class Player(val id: PlayerId, val weapon: Weapon)
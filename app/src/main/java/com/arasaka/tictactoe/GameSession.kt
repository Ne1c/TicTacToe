package com.arasaka.tictactoe

import kotlin.math.abs
import kotlin.random.Random

class GameSession {
    private var currentTurn: Player

    val player1: Player
    val player2: Player

    init {
        val whoStartsId = whoStarts()
        val whoStartsWeapon = Weapon.X

        val opponent = if (whoStartsId == PlayerId.PLAYER_1) {
            PlayerId.PLAYER_2
        } else {
            PlayerId.PLAYER_1
        }

        val opponentWeapon = Weapon.O

        player1 = Player(whoStartsId, whoStartsWeapon)
        player2 = Player(opponent, opponentWeapon)

        currentTurn = player1
    }

    fun currentTurn(): Player = currentTurn

    fun nextTurn(): Player {
        val next = if (currentTurn() == player1) {
            player2
        } else {
            player1
        }

        currentTurn = next
        return next
    }

    private fun whoStarts(): PlayerId {
        return PlayerId.values()[abs(Random.nextInt() % PlayerId.values().size)]
    }
}
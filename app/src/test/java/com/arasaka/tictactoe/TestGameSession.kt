package com.arasaka.tictactoe

import org.junit.Before
import org.junit.Test

class TestGameSession {
    private lateinit var session: GameSession

    @Before
    fun setUp() {
        session = GameSession()
    }

    @Test
    fun `verify that player who turns first uses X as a weapon`() {
        assert(session.currentTurn().weapon == Weapon.X)
    }

    @Test
    fun `verify that player changes after turn`() {
        val currentPlayer = session.currentTurn()
        val nextPlayer = session.nextTurn()

        assert(currentPlayer != nextPlayer)
        assert(currentPlayer.id != nextPlayer.id)
    }

    @Test
    fun `verify unique weapon for each player`() {
        assert(session.player1.weapon != session.player2.weapon)
    }
}
package com.arasaka.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TestGameViewModel {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: GameViewModel

    @Before
    fun setUp() {
        viewModel = GameViewModel()
    }

    @Test
    fun `verify start status`() {
        viewModel.start()

        val board = viewModel.gameBoardValues.getOrAwaitValue()

        assert(viewModel.gameState.getOrAwaitValue() == GameState.STARTED)
        assert(viewModel.playerTurn.getOrAwaitValue() != null)

        board.forEach { assert(it == GameBoardValue.NO_VALUE) }
    }

    @Test
    fun `verify makeTurn updates`() {
        viewModel.start()

        val firstPlayer = viewModel.playerTurn.getOrAwaitValue()

        viewModel.makeTurn(0)

        val board = viewModel.gameBoardValues.getOrAwaitValue()

        assert(viewModel.gameState.getOrAwaitValue() == GameState.STARTED)
        assert(board[0] == firstPlayer.weapon.toGameFieldValue())
        assert(viewModel.playerTurn.getOrAwaitValue().id != firstPlayer.id)
    }

    /**
     * x|x|o
     * x|o|o
     * x|o|x
     */
    @Test
    fun `verify the victory of player1`() {
        viewModel.start()

        val firstPlayer = viewModel.playerTurn.getOrAwaitValue()

        // x turn
        viewModel.makeTurn(0)
        // o turn
        viewModel.makeTurn(2)
        // x turn
        viewModel.makeTurn(1)
        // o turn
        viewModel.makeTurn(5)
        // x turn
        viewModel.makeTurn(3)
        // o turn
        viewModel.makeTurn(4)
        // x turn
        viewModel.makeTurn(6)
        // o turn
        viewModel.makeTurn(7)
        // x turn
        viewModel.makeTurn(8)


        assert(viewModel.gameState.getOrAwaitValue() == GameState.FINISHED)
        assert(viewModel.winner.getOrAwaitValue() == firstPlayer.id)
    }

    /**
     * x|o|x
     * o|x|x
     * o|x|o
     */
    @Test
    fun `verify tie result`() {
        viewModel.start()

        // x turn
        viewModel.makeTurn(0)
        // o turn
        viewModel.makeTurn(1)
        // x turn
        viewModel.makeTurn(2)
        // o turn
        viewModel.makeTurn(3)
        // x turn
        viewModel.makeTurn(4)
        // o turn
        viewModel.makeTurn(6)
        // x turn
        viewModel.makeTurn(5)
        // o turn
        viewModel.makeTurn(8)
        // x turn
        viewModel.makeTurn(7)


        assert(viewModel.gameState.getOrAwaitValue() == GameState.FINISHED)
        assert(viewModel.winner.getOrAwaitValue() == null)
    }

    @Test
    fun `verify that sessions restarts correctly after previous one`() {
        viewModel.start()

        // x turn
        viewModel.makeTurn(0)
        // o turn
        viewModel.makeTurn(2)
        // x turn
        viewModel.makeTurn(1)
        // o turn
        viewModel.makeTurn(5)
        // x turn
        viewModel.makeTurn(3)
        // o turn
        viewModel.makeTurn(4)
        // x turn
        viewModel.makeTurn(6)
        // o turn
        viewModel.makeTurn(7)
        // x turn
        viewModel.makeTurn(8)

        viewModel.start()

        val board = viewModel.gameBoardValues.getOrAwaitValue()

        assert(viewModel.gameState.getOrAwaitValue() == GameState.STARTED)
        assert(viewModel.playerTurn.getOrAwaitValue() != null)

        board.forEach { assert(it == GameBoardValue.NO_VALUE) }
    }
}
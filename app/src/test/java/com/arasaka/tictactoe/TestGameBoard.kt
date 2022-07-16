package com.arasaka.tictactoe

import org.junit.Before
import org.junit.Test

class TestGameBoard {
    private lateinit var gameBoard: GameBoard

    @Before
    fun setUp() {
        gameBoard = GameBoard()
    }

    /**
     * x|x|o
     * x|o|o
     * x|o|x
     */
    @Test
    fun `verify first column winner`() {
        val player1 = Player(PlayerId.PLAYER_1, Weapon.X)
        val player2 = Player(PlayerId.PLAYER_2, Weapon.O)

        gameBoard.makeTurn(0, GameBoardValue.X)
        gameBoard.makeTurn(1, GameBoardValue.X)
        gameBoard.makeTurn(2, GameBoardValue.O)
        gameBoard.makeTurn(3, GameBoardValue.X)
        gameBoard.makeTurn(4, GameBoardValue.O)
        gameBoard.makeTurn(5, GameBoardValue.O)
        gameBoard.makeTurn(6, GameBoardValue.X)
        gameBoard.makeTurn(7, GameBoardValue.O)
        gameBoard.makeTurn(8, GameBoardValue.X)

        assert(gameBoard.isThereWinner(player1, player2) == player1)
    }

    /**
     * o|x|o
     * o|x|x
     * x|x|o
     */
    @Test
    fun `verify second column winner`() {
        val player1 = Player(PlayerId.PLAYER_1, Weapon.X)
        val player2 = Player(PlayerId.PLAYER_2, Weapon.O)

        gameBoard.makeTurn(0, GameBoardValue.O)
        gameBoard.makeTurn(1, GameBoardValue.X)
        gameBoard.makeTurn(2, GameBoardValue.O)
        gameBoard.makeTurn(3, GameBoardValue.O)
        gameBoard.makeTurn(4, GameBoardValue.X)
        gameBoard.makeTurn(5, GameBoardValue.X)
        gameBoard.makeTurn(6, GameBoardValue.X)
        gameBoard.makeTurn(7, GameBoardValue.X)
        gameBoard.makeTurn(8, GameBoardValue.O)

        assert(gameBoard.isThereWinner(player1, player2) == player1)
    }

    /**
     * o|x|x
     * o|x|x
     * o|o|x
     */
    @Test
    fun `verify third column winner`() {
        val player1 = Player(PlayerId.PLAYER_1, Weapon.X)
        val player2 = Player(PlayerId.PLAYER_2, Weapon.O)

        gameBoard.makeTurn(0, GameBoardValue.O)
        gameBoard.makeTurn(1, GameBoardValue.X)
        gameBoard.makeTurn(2, GameBoardValue.X)
        gameBoard.makeTurn(3, GameBoardValue.O)
        gameBoard.makeTurn(4, GameBoardValue.X)
        gameBoard.makeTurn(5, GameBoardValue.X)
        gameBoard.makeTurn(6, GameBoardValue.O)
        gameBoard.makeTurn(7, GameBoardValue.O)
        gameBoard.makeTurn(8, GameBoardValue.X)

        assert(gameBoard.isThereWinner(player1, player2) == player1)
    }

    /**
     * x|x|x
     * o|x|o
     * o|o|x
     */
    @Test
    fun `verify first row winner`() {
        val player1 = Player(PlayerId.PLAYER_1, Weapon.X)
        val player2 = Player(PlayerId.PLAYER_2, Weapon.O)

        gameBoard.makeTurn(0, GameBoardValue.X)
        gameBoard.makeTurn(1, GameBoardValue.X)
        gameBoard.makeTurn(2, GameBoardValue.X)
        gameBoard.makeTurn(3, GameBoardValue.O)
        gameBoard.makeTurn(4, GameBoardValue.X)
        gameBoard.makeTurn(5, GameBoardValue.O)
        gameBoard.makeTurn(6, GameBoardValue.O)
        gameBoard.makeTurn(7, GameBoardValue.O)
        gameBoard.makeTurn(8, GameBoardValue.X)

        assert(gameBoard.isThereWinner(player1, player2) == player1)
    }

    /**
     * o|x|o
     * x|x|x
     * o|o|x
     */
    @Test
    fun `verify second row winner`() {
        val player1 = Player(PlayerId.PLAYER_1, Weapon.X)
        val player2 = Player(PlayerId.PLAYER_2, Weapon.O)

        gameBoard.makeTurn(0, GameBoardValue.O)
        gameBoard.makeTurn(1, GameBoardValue.X)
        gameBoard.makeTurn(2, GameBoardValue.O)
        gameBoard.makeTurn(3, GameBoardValue.X)
        gameBoard.makeTurn(4, GameBoardValue.X)
        gameBoard.makeTurn(5, GameBoardValue.X)
        gameBoard.makeTurn(6, GameBoardValue.O)
        gameBoard.makeTurn(7, GameBoardValue.O)
        gameBoard.makeTurn(8, GameBoardValue.X)

        assert(gameBoard.isThereWinner(player1, player2) == player1)
    }

    /**
     * o|x|o
     * o|o|x
     * x|x|x
     */
    @Test
    fun `verify third row winner`() {
        val player1 = Player(PlayerId.PLAYER_1, Weapon.X)
        val player2 = Player(PlayerId.PLAYER_2, Weapon.O)

        gameBoard.makeTurn(0, GameBoardValue.O)
        gameBoard.makeTurn(1, GameBoardValue.X)
        gameBoard.makeTurn(2, GameBoardValue.O)
        gameBoard.makeTurn(3, GameBoardValue.O)
        gameBoard.makeTurn(4, GameBoardValue.O)
        gameBoard.makeTurn(5, GameBoardValue.X)
        gameBoard.makeTurn(6, GameBoardValue.X)
        gameBoard.makeTurn(7, GameBoardValue.X)
        gameBoard.makeTurn(8, GameBoardValue.X)

        assert(gameBoard.isThereWinner(player1, player2) == player1)
    }

    /**
     * o|o|x
     * o|x|o
     * x|o|o
     */
    @Test
    fun `verify right to left diagonal winner`() {
        val player1 = Player(PlayerId.PLAYER_1, Weapon.X)
        val player2 = Player(PlayerId.PLAYER_2, Weapon.O)

        gameBoard.makeTurn(0, GameBoardValue.O)
        gameBoard.makeTurn(1, GameBoardValue.O)
        gameBoard.makeTurn(2, GameBoardValue.X)
        gameBoard.makeTurn(3, GameBoardValue.O)
        gameBoard.makeTurn(4, GameBoardValue.X)
        gameBoard.makeTurn(5, GameBoardValue.O)
        gameBoard.makeTurn(6, GameBoardValue.X)
        gameBoard.makeTurn(7, GameBoardValue.O)
        gameBoard.makeTurn(8, GameBoardValue.O)

        assert(gameBoard.isThereWinner(player1, player2) == player1)
    }

    /**
     * x|o|o
     * o|x|o
     * o|o|x
     */
    @Test
    fun `verify left to right diagonal winner`() {
        val player1 = Player(PlayerId.PLAYER_1, Weapon.X)
        val player2 = Player(PlayerId.PLAYER_2, Weapon.O)

        gameBoard.makeTurn(0, GameBoardValue.X)
        gameBoard.makeTurn(1, GameBoardValue.O)
        gameBoard.makeTurn(2, GameBoardValue.O)
        gameBoard.makeTurn(3, GameBoardValue.O)
        gameBoard.makeTurn(4, GameBoardValue.X)
        gameBoard.makeTurn(5, GameBoardValue.O)
        gameBoard.makeTurn(6, GameBoardValue.O)
        gameBoard.makeTurn(7, GameBoardValue.O)
        gameBoard.makeTurn(8, GameBoardValue.X)

        assert(gameBoard.isThereWinner(player1, player2) == player1)
    }

    /**
     * x|o|o
     * o|x|x
     * o|x|o
     */
    @Test
    fun `verify tie result`() {
        val player1 = Player(PlayerId.PLAYER_1, Weapon.X)
        val player2 = Player(PlayerId.PLAYER_2, Weapon.O)

        gameBoard.makeTurn(0, GameBoardValue.X)
        gameBoard.makeTurn(1, GameBoardValue.O)
        gameBoard.makeTurn(2, GameBoardValue.O)
        gameBoard.makeTurn(3, GameBoardValue.O)
        gameBoard.makeTurn(4, GameBoardValue.X)
        gameBoard.makeTurn(5, GameBoardValue.X)
        gameBoard.makeTurn(6, GameBoardValue.O)
        gameBoard.makeTurn(7, GameBoardValue.X)
        gameBoard.makeTurn(8, GameBoardValue.O)

        assert(gameBoard.isBoardFull())
        assert(gameBoard.isThereWinner(player1, player2) == null)
    }

    @Test
    fun `make turn at a free cell`() {
        gameBoard.makeTurn(0, GameBoardValue.X)

        assert(gameBoard.board[0][0] == GameBoardValue.X)
    }

    @Test
    fun `make turn at a occupied cell`() {
        gameBoard.makeTurn(0, GameBoardValue.X)
        gameBoard.makeTurn(0, GameBoardValue.O)

        assert(gameBoard.board[0][0] == GameBoardValue.X)
    }

    @Test
    fun `verify turn for a free cell`() {
        gameBoard.makeTurn(0, GameBoardValue.X)

        assert(gameBoard.isValidTurn(1))
    }

    @Test
    fun `verify turn for the occupied cell`() {
        gameBoard.makeTurn(0, GameBoardValue.X)

        assert(!gameBoard.isValidTurn(0))
    }

    @Test
    fun `verify that isBoardFull() returns false when board is empty`() {
        assert(!gameBoard.isBoardFull())
    }

    @Test
    fun `verify that isBoardFull() returns true when board is full`() {
        gameBoard.makeTurn(0, GameBoardValue.X)
        gameBoard.makeTurn(1, GameBoardValue.X)
        gameBoard.makeTurn(2, GameBoardValue.X)
        gameBoard.makeTurn(3, GameBoardValue.X)
        gameBoard.makeTurn(4, GameBoardValue.X)
        gameBoard.makeTurn(5, GameBoardValue.X)
        gameBoard.makeTurn(6, GameBoardValue.X)
        gameBoard.makeTurn(7, GameBoardValue.X)
        gameBoard.makeTurn(8, GameBoardValue.X)

        assert(gameBoard.isBoardFull())
    }

    @Test
    fun `verify that isBoardFull() returns false when the board is half full`() {
        gameBoard.makeTurn(0, GameBoardValue.X)
        gameBoard.makeTurn(1, GameBoardValue.X)
        gameBoard.makeTurn(2, GameBoardValue.X)
        gameBoard.makeTurn(3, GameBoardValue.X)
        gameBoard.makeTurn(4, GameBoardValue.X)

        assert(!gameBoard.isBoardFull())
    }

    @Test
    fun `verify reset`() {
        gameBoard.makeTurn(0, GameBoardValue.X)
        gameBoard.makeTurn(5, GameBoardValue.O)
        gameBoard.makeTurn(6, GameBoardValue.X)

        gameBoard.reset()

        val board = gameBoard.board

        for (i in board.indices) {
            for (j in board[i].indices) {
                assert(board[i][j] == GameBoardValue.NO_VALUE)
            }
        }
    }
}
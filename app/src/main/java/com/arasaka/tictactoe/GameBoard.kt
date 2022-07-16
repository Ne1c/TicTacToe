package com.arasaka.tictactoe

import kotlin.math.abs

class GameBoard {
    val board = Array(3) { Array(3) { GameBoardValue.NO_VALUE } }

    fun isThereWinner(player1: Player, player2: Player): Player? {
        var result = GameBoardValue.NO_VALUE

        // Rows
        for (i in 0 until 3) {
            if (board[i][0] == board[i][1] &&
                board[i][1] == board[i][2] &&
                board[i][0] != GameBoardValue.NO_VALUE
            ) {
                result = board[i][0]
            }
        }

        // Columns
        for (j in 0 until 3) {
            if (board[0][j] == board[1][j] &&
                board[1][j] == board[2][j] &&
                board[0][j] != GameBoardValue.NO_VALUE
            ) {
                result = board[0][j]
            }
        }

        // Diagonals
        if (board[0][0] == board[1][1] &&
            board[1][1] == board[2][2] &&
            board[0][0] != GameBoardValue.NO_VALUE
        ) {
            result = board[0][0];
        }

        if (board[2][0] == board[1][1] &&
            board[1][1] == board[0][2] &&
            board[2][0] != GameBoardValue.NO_VALUE
        ) {
            result = board[2][0];
        }

        return if (result == GameBoardValue.NO_VALUE) {
            null
        } else if (result == GameBoardValue.X) {
            if (player1.weapon == Weapon.X) {
                return player1
            } else {
                return player2
            }
        } else {
            if (player1.weapon == Weapon.O) {
                return player1
            } else {
                return player2
            }
        }
    }

    fun makeTurn(cell: Int, value: GameBoardValue) {
        if (!isValidTurn(cell)) return
        if (isBoardFull()) return

        val (i, j) = convertCellIndexToArrayIndices(cell)
        board[i][j] = value
    }

    fun isValidTurn(cell: Int): Boolean {
        val (i, j) = convertCellIndexToArrayIndices(cell)

        return board[i][j] == GameBoardValue.NO_VALUE
    }

    fun isBoardFull(): Boolean {
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == GameBoardValue.NO_VALUE) {
                    return false
                }
            }
        }

        return true
    }

    fun reset() {
        for (i in board.indices) {
            for (j in board[i].indices) {
                board[i][j] = GameBoardValue.NO_VALUE
            }
        }
    }

    private fun convertCellIndexToArrayIndices(cell: Int): Pair<Int, Int> {
        return if (cell < 3) {
            0 to cell
        } else if (cell < 6) {
            1 to abs(3 - cell)
        } else if (cell < 9) {
            2 to abs(6 - cell)
        } else {
            throw IllegalArgumentException("Invalid cell index")
        }
    }
}
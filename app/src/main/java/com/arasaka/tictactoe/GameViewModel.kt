package com.arasaka.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val gameBoard = GameBoard()

    private val _gameState = MutableLiveData(GameState.NOT_STARTED)
    private val _playerTurn = MutableLiveData<Player>()
    private val _winner = MutableLiveData<PlayerId?>()
    private val _gameBoardValues = MutableLiveData<Array<GameBoardValue>>()

    val gameState: LiveData<GameState> get() = _gameState
    val playerTurn: LiveData<Player> get() = _playerTurn
    val winner: LiveData<PlayerId?> get() = _winner
    val gameBoardValues: LiveData<Array<GameBoardValue>> get() = _gameBoardValues

    private var gameSession: GameSession? = null

    fun start() {
        gameSession = GameSession()

        gameBoard.reset()

        _gameBoardValues.value = mapGameFieldToLinearUiArray(gameBoard.board)
        _gameState.value = GameState.STARTED
        _playerTurn.value = checkNotNull(gameSession).currentTurn()

    }

    fun makeTurn(cell: Int) {
        if (gameState.value == GameState.NOT_STARTED || gameState.value == GameState.FINISHED) return
        if (!gameBoard.isValidTurn(cell)) return

        gameBoard.makeTurn(cell, checkNotNull(gameSession).currentTurn().weapon.toGameFieldValue())
        _gameBoardValues.value = mapGameFieldToLinearUiArray(gameBoard.board)

        val winner = gameBoard.isThereWinner(
            checkNotNull(gameSession).player1,
            checkNotNull(gameSession).player2
        )

        if (winner != null) {
            finish(winner)
        } else if (gameBoard.isBoardFull()) {
            finish(null)
        } else {
            _playerTurn.value = checkNotNull(gameSession).nextTurn()
        }
    }

    private fun finish(winner: Player?) {
        if (_gameState.value == GameState.FINISHED || _gameState.value == GameState.NOT_STARTED) return

        _gameState.value = GameState.FINISHED
        _winner.value = winner?.id

        gameSession = null
    }
}
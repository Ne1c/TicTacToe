package com.arasaka.tictactoe

fun mapGameFieldToLinearUiArray(gameField: Array<Array<GameBoardValue>>): Array<GameBoardValue> {
    val array = Array(9) { GameBoardValue.NO_VALUE }
    var destArrIndex = 0

    for (i in gameField.indices) {
        for (j in gameField[i].indices) {
            array[destArrIndex++] = gameField[i][j]
        }
    }

    return array
}

fun Weapon.toGameFieldValue(): GameBoardValue {
    return if (this == Weapon.X) {
        GameBoardValue.X
    } else {
        GameBoardValue.O
    }
}
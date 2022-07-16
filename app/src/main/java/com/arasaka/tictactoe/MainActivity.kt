package com.arasaka.tictactoe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ComposeView(this).apply {
            setContent {
                GameScreen()
            }
        })
    }
}

@Composable
fun GameScreen(viewModel: GameViewModel = viewModel()) {
    val gameBoardValues = viewModel.gameBoardValues.observeAsState(Array(9) { GameBoardValue.NO_VALUE })
    val gameState = viewModel.gameState.observeAsState(GameState.NOT_STARTED)
    val playerTurn = viewModel.playerTurn.observeAsState()
    val winner = viewModel.winner.observeAsState()

    GameContent(gameBoardValues, gameState, playerTurn, winner, onStartClick = {
        viewModel.start()
    }, onCellClick = {
        viewModel.makeTurn(it)
    })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameContent(
    gameBoardValues: State<Array<GameBoardValue>>,
    gameState: State<GameState>,
    playerTurn: State<Player?>,
    winner: State<PlayerId?>,
    onStartClick: () -> Unit,
    onCellClick: (Int) -> Unit
) {
    val size = 276.dp
    val buttonSize = 92.dp

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.arasaka_logo),
            contentDescription = null,
            modifier = Modifier
                .height(128.dp)
                .padding(all = 16.dp)
        )

        Row(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        ) {
            val text = if (gameState.value == GameState.NOT_STARTED) {
                "Wanna play?"
            } else if (gameState.value == GameState.STARTED) {
                "${playerTurn.value?.id}'s turn"
            } else {
                if (winner.value == null) {
                    "Tie"
                } else {
                    "${playerTurn.value?.id} won"
                }
            }

            Text(
                text = text,
                fontSize = 26.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            if (gameState.value == GameState.STARTED) {
                playerTurn.value?.let {
                    Icon(
                        painter = painterResource(id = if (it.weapon == Weapon.X) R.drawable.ic_cross else R.drawable.ic_circle),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .size(size = size)
        ) {
            Spacer(
                Modifier
                    .padding(start = buttonSize)
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(color = Color.Black)
            )

            Spacer(
                Modifier
                    .padding(start = buttonSize * 2)
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(color = Color.Black)
            )

            Spacer(
                Modifier
                    .padding(top = buttonSize)
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(color = Color.Black)
            )

            Spacer(
                Modifier
                    .padding(top = buttonSize * 2)
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(color = Color.Black)
            )

            LazyVerticalGrid(modifier = Modifier.size(size = size), cells = GridCells.Fixed(3)) {
                itemsIndexed(gameBoardValues.value) { index, item ->
                    IconButton(onClick = { onCellClick.invoke(index) }, modifier = Modifier.size(92.dp, 92.dp)) {
                        if (item == GameBoardValue.X) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cross),
                                contentDescription = null,
                                modifier = Modifier.size(36.dp)
                            )
                        } else if (item == GameBoardValue.O) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_circle),
                                contentDescription = null,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }
                }
            }
        }

        AnimatedVisibility(visible = gameState.value == GameState.NOT_STARTED || gameState.value == GameState.FINISHED) {
            Column {
                Button(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 24.dp),
                    onClick = onStartClick
                ) {
                    Text(text = "Start Game")
                }
            }
        }
    }
}

@Preview
@Composable
fun GameContentPreview() {
    GameContent(
        gameBoardValues = derivedStateOf { (Array(9) { GameBoardValue.NO_VALUE }) },
        gameState = derivedStateOf { GameState.NOT_STARTED },
        playerTurn = derivedStateOf { null },
        winner = derivedStateOf { null },
        onStartClick = {},
        onCellClick = {}
    )
}
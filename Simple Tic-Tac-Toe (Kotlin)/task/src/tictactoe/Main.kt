package tictactoe

import kotlin.system.exitProcess

fun display(list: MutableList<MutableList<String>>) {
    println("---------")
    var index = 0
    repeat(3) {
        println("| ${list[index++].joinToString(" ")} |")
    }
    println("---------")
}


fun main() {

    val board = mutableListOf(
        mutableListOf(" ", " ", " "),
        mutableListOf(" ", " ", " "),
        mutableListOf(" ", " ", " ")
    )

//    val matrix = mutableListOf(
//        mutableListOf(board[0], board[1], board[2]),
//        mutableListOf(board[3], board[4], board[5]),
//        mutableListOf(board[6], board[7], board[8]),
//    )

    display(board)

    while (true) {

        val (x, y) = inputCoordinates()

        if (!x.isDigit() && !y.isDigit()) {
            println("You should enter numbers!")
            continue
        }

        if (x.toInt() !in 1..3 || y.toInt() !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            continue
        }

        val step = board[x.toInt() - 1][y.toInt() - 1]

        when (step) {
            "_", " " -> {
                board[x.toInt() - 1][y.toInt() - 1] = "X"
                display(board)
                calculate(board)
                continue
            }

            "X", "O" -> {
                println("This cell is occupied! Choose another one!")
                continue
            }
        }
    }

}

fun calculate(list: MutableList<MutableList<String>>) {
    val s = list.onEach { it.joinToString() }
    when {

        s[0].all { it == "X" } ||
                s[1].all { it == "X" } ||
                s[2].all { it == "X" } -> println("X wins")
            .also { exitProcess(0) }

        s[0].all { it == "O" } ||
                s[1].all { it == "O" } ||
                s[2].all { it == "O" } -> println("O wins")
            .also { exitProcess(0) }

        s[0][0] == "X" && s[1][1] == "X" && s[2][2] == "X" ||
                s[0][2] == "X" && s[1][1] == "X" && s[2][0] == "X" -> println("X wins")
            .also { exitProcess(0) }

        s[0][0] == "O" && s[1][1] == "O" && s[2][2] == "O" ||
                s[0][2] == "O" && s[1][1] == "O" && s[2][0] == "O" -> println("O wins")
            .also { exitProcess(0) }
    }
}


//    val rows = listOf(
//        board.substring(0, 3),
//        board.substring(3, 6),
//        board.substring(6, 9)
//    )
//
//    val cols = listOf(
//        (0..6 step 3).map { board[it] }.joinToString(""),
//        (1..7 step 3).map { board[it] }.joinToString(""),
//        (2..8 step 3).map { board[it] }.joinToString("")
//    )
//
//    val diags = listOf(
//        "${board[0]}${board[4]}${board[8]}",
//        "${board[2]}${board[4]}${board[6]}"
//    )
//
//    val lines = rows + cols + diags
//
//    val xWins = lines.any { it == "XXX" }
//    val oWins = lines.any { it == "OOO" }
//
//    val numX = board.count { it == 'X' }
//    val numO = board.count { it == 'O' }
//    val numEmpty = board.count { it == '_' }
//
//    println("---------")
//    for (row in board.chunked(3)) {
//        println("| ${row[0]} ${row[1]} ${row[2]} |")
//    }
//    println("---------")
//
//    when {
//        Math.abs(numX - numO) > 1 -> println("Impossible")
//        xWins && oWins -> println("Impossible")
//        xWins -> println("X wins")
//        oWins -> println("O wins")
//        numEmpty > 0 -> println("Game not finished")
//        else -> println("Draw")
//    }


private fun inputCoordinates() = readln().split(" ")

fun String.isDigit() = all { it.isDigit() }
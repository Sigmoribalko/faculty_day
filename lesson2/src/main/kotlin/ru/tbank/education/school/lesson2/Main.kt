package ru.tbank.education.school.lesson2

fun main() {
    data class position(val row: Int, val col: Int) {
        init {
            if (row < 0 or (row > 7) or (col < 0) or (col > 7)) {
                println("Ошибка: Позиция вне доски")
            }
        }
    }
    enum class color {
        white, black
    }
    sealed class moveResult {
        data class success(val from: position, val to: position) : moveResult()
        data class invalid(val reason: String) : moveResult()
        data class capture(val from: position, val to: position, val captured: String) : moveResult()
    }
    abstract class chesspiece(
        val color: color,
        protected var position: position
    ) {
        private var movecount = 0
        val moves: Int
            get() = movecount
        abstract val symbol: String
        open fun moveto(newposition: position): moveResult {
            val oldposition = position
            position = newposition
            movecount++
            return moveResult.success(oldposition, newposition)
        }
        override fun toString(): String = "$symbol на ${position.row},${position.col}"
    }
    open class king(color: color, position: position) : chesspiece(color, position) {
        override val symbol = if (color == color.white) "♔" else "♚"

        constructor(color: color, row: Int, col: Int) : this(color, position(row, col))
    }
    class queen(color: color, position: position) : chesspiece(color, position) {
        override val symbol = if (color == color.white) "♕" else "♛"

        constructor(color: color, row: Int, col: Int) : this(color, position(row, col))
    }
    class pawn(color: color, position: position) : chesspiece(color, position) {
        override val symbol = if (color == color.white) "♙" else "♟"
    }
    class chessboard {
        private val pieces = mutableListOf<chesspiece>()
        fun addpiece(piece: chesspiece) {
            pieces.add(piece)
        }
        fun movepiece(piece: chesspiece, target: position): moveResult {
            val result = piece.moveto(target)
            when (result) {
                is moveResult.success -> println("${piece.symbol} переместился на ${result.to.row},${result.to.col}")
                is moveResult.invalid -> println("Ошибка: ${result.reason}")
                is moveResult.capture -> println("${piece.symbol} захватил фигуру")
            }
            return result
        }
        fun displayboard() {
            println("\nТекущее состояние доски:")
            for (piece in pieces) {
                println("  $piece (ходов: ${piece.moves})")
            }
        }
    }
    class game(private val board: chessboard) {
        private val players = mapOf(
            color.white to "Белые",
            color.black to "Черные"
        )
        fun start() {
            println("=== Шахматная партия начинается ===\n")
            board.displayboard()
        }
        fun getplayername(color: color): String = players[color] ?: "Неизвестный"
    }
    val board = chessboard()
    val whiteking = king(color.white, 7, 4)
    val whitequeen = queen(color.white, 7, 3)
    val whitepawn = pawn(color.white, position(6, 4))
    val blackking = king(color.black, 0, 4)
    board.addpiece(whiteking)
    board.addpiece(whitequeen)
    board.addpiece(whitepawn)
    board.addpiece(blackking)
    val game = game(board)
    game.start()
    println("\n--- Ходы ---")
    board.movepiece(whitepawn, position(5, 4))
    board.movepiece(whitequeen, position(3, 3))
    board.movepiece(whiteking, position(7, 5))
    board.displayboard()
}

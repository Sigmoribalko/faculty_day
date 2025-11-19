package ru.tbank.education.school.lesson2

fun main() {
    data class Position(val row: Int, val col: Int) {
        init {
            if (row < 0 or (row > 7) or (col < 0) or (col > 7)) {
                println("Ошибка: Позиция вне доски")
            }
        }
    }

    enum class Color {
        WHITE, BLACK
    }

    sealed class MoveResult {
        data class Success(val from: Position, val to: Position) : MoveResult()
        data class Invalid(val reason: String) : MoveResult()
        data class Capture(val from: Position, val to: Position, val captured: String) : MoveResult()
    }

    abstract class ChessPiece(
        val color: Color,
        protected var position: Position
    ) {
        private var moveCount = 0

        val moves: Int
            get() = moveCount

        abstract val symbol: String

        open fun moveTo(newPosition: Position): MoveResult {
            val oldPosition = position
            position = newPosition
            moveCount++
            return MoveResult.Success(oldPosition, newPosition)
        }

        override fun toString(): String = "$symbol на ${position.row},${position.col}"
    }

    open class King(color: Color, position: Position) : ChessPiece(color, position) {
        override val symbol = if (color == Color.WHITE) "♔" else "♚"

        constructor(color: Color, row: Int, col: Int) : this(color, Position(row, col))
    }

    class Queen(color: Color, position: Position) : ChessPiece(color, position) {
        override val symbol = if (color == Color.WHITE) "♕" else "♛"

        constructor(color: Color, row: Int, col: Int) : this(color, Position(row, col))
    }

    class Pawn(color: Color, position: Position) : ChessPiece(color, position) {
        override val symbol = if (color == Color.WHITE) "♙" else "♟"
    }

    class ChessBoard {
        private val pieces = mutableListOf<ChessPiece>()

        fun addPiece(piece: ChessPiece) {
            pieces.add(piece)
        }

        fun movePiece(piece: ChessPiece, target: Position): MoveResult {
            val result = piece.moveTo(target)
            when (result) {
                is MoveResult.Success -> println("${piece.symbol} переместился на ${result.to.row},${result.to.col}")
                is MoveResult.Invalid -> println("Ошибка: ${result.reason}")
                is MoveResult.Capture -> println("${piece.symbol} захватил фигуру")
            }
            return result
        }

        fun displayBoard() {
            println("\nТекущее состояние доски:")
            for (piece in pieces) {
                println("  $piece (ходов: ${piece.moves})")
            }
        }
    }

    class Game(private val board: ChessBoard) {
        private val players = mapOf(
            Color.WHITE to "Белые",
            Color.BLACK to "Черные"
        )

        fun start() {
            println("=== Шахматная партия начинается ===\n")
            board.displayBoard()
        }

        fun getPlayerName(color: Color): String = players[color] ?: "Неизвестный"
    }

    val board = ChessBoard()

    val whiteKing = King(Color.WHITE, 7, 4)
    val whiteQueen = Queen(Color.WHITE, 7, 3)
    val whitePawn = Pawn(Color.WHITE, Position(6, 4))
    val blackKing = King(Color.BLACK, 0, 4)

    board.addPiece(whiteKing)
    board.addPiece(whiteQueen)
    board.addPiece(whitePawn)
    board.addPiece(blackKing)

    val game = Game(board)
    game.start()

    println("\n--- Ходы ---")
    board.movePiece(whitePawn, Position(5, 4))
    board.movePiece(whiteQueen, Position(3, 3))
    board.movePiece(whiteKing, Position(7, 5))

    board.displayBoard()
}

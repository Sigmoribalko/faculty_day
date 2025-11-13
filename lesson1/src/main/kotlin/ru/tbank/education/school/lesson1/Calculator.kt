package ru.tbank.education.school.lesson1

/**
 * Метод для вычисления простых арифметических операций.
 */
fun calculate(a: Double, b: Double, operation: OperationType = OperationType.ADD): Double? {
    val result = when (operation) {
        OperationType.ADD -> a + b
        OperationType.SUBTRACT -> a - b
        OperationType.MULTIPLY -> a * b
        OperationType.DIVIDE -> {
            if (b == 0.0) {
                null
            } else {
                a / b
            }
        }
    }
    if (result != null) {
    println(result)
    } else {
        println("Ошибка: деление на ноль")
    }
    return result
}

/**
 * Функция вычисления выражения, представленного строкой
 * @return результат вычисления строки или null, если вычисление невозможно
 * @sample "5 * 2".calculate()
 */
@Suppress("ReturnCount")
fun String.calculate(): Double? {
    val sumbls = this.split(" ")
    if (sumbls.size != 3) return null
    val a = sumbls[0].toDoubleOrNull()
    if (a == null) {
        return null
    }
    val first = a
    val b = sumbls[2].toDoubleOrNull()
    if (b == null) {
        return null
    }
    val sec = b
    
    val operation = when (sumbls[1]) {
        "+" -> OperationType.ADD
        "-" -> OperationType.SUBTRACT
        "*" -> OperationType.MULTIPLY
        "/" -> OperationType.DIVIDE
        else -> return null
    }
    return calculate(first, sec, operation)
}
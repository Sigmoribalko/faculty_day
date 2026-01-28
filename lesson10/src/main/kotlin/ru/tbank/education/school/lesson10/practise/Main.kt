import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun main() {
    task1()
    println()
    task2()
    println()
    task3()
    println()
    task4()
    println()
    task5()
    println()
    task6()
    println()
    task7()
    println()
    task8()
}

/*
1) Строки + регулярные выражения
["Name: Ivan, score=17", ...]
Извлечь имя и score, собрать пары, вывести победителя.
*/
fun task1() {
    val lines = listOf(
        "Name: Ivan, score=17",
        "Name: Olga, score=23",
        "Name: Max, score=5"
    )

    val re = Regex("""^Name:\s*([A-Za-z]+)\s*,\s*score=(\d+)\s*$""")

    val pairs: List<Pair<String, Int>> = lines.mapNotNull { s ->
        val m = re.find(s) ?: return@mapNotNull null
        val name = m.groupValues[1]
        val score = m.groupValues[2].toInt()
        name to score
    }

    println("Пары (имя, очки): $pairs")

    val best = pairs.maxByOrNull { it.second }
    if (best != null) {
        println("Лучший результат: ${best.first} (${best.second})")
    } else {
        println("Нет валидных строк")
    }
}

/*
2) Даты + коллекции
["2026-01-22", ...]
Преобразовать в даты, отсортировать, посчитать сколько в январе 2026.
*/
fun task2() {
    val dateStrings = listOf(
        "2026-01-22",
        "2026-02-01",
        "2025-12-31",
        "2026-01-05"
    )

    val fmt = DateTimeFormatter.ISO_LOCAL_DATE

    val dates = dateStrings.map { LocalDate.parse(it, fmt) }.sorted()

    println("Отсортированные даты: ${dates.joinToString { it.format(fmt) }}")

    val countJan2026 = dates.count { it.year == 2026 && it.month == Month.JANUARY }
    println("Количество дат в январе 2026: $countJan2026")
}

/*
3) Коллекции + строки
"apple orange apple banana orange apple"
Частоты слов, вывести слова с частотой > 1 по алфавиту.
*/
fun task3() {
    val text = "apple orange apple banana orange apple"

    val words = text.trim().split(Regex("""\s+""")).filter { it.isNotEmpty() }

    val freq = mutableMapOf<String, Int>()
    for (w in words) {
        freq[w] = (freq[w] ?: 0) + 1
    }

    println("Частота слов: $freq")

    val repeated = freq
        .filter { (_, c) -> c > 1 }
        .keys
        .sorted()

    println("Повторяющиеся слова: ${repeated.joinToString(", ")}")
}

fun task4() {
    val strings = listOf("A-123", "B-7", "AA-12", "C-001", "D-99x")
    
    val pattern = Regex("^[A-Z]-\\d{1,3}$")
    val filtered = strings.filter { pattern.matches(it) }
    
    println("Отфильтрованные строки: $filtered")
}

fun task5() {
    val strings = listOf("  Hello   world  ", "A   B    C", "   one")
    
    val normalized = strings.map { 
        it.trim().replace(Regex("\\s+"), " ")
    }
    
    println("Нормализованные строки: $normalized")
}

fun task6() {
    val datePairs = listOf(
        "2026-01-01" to "2026-01-10",
        "2025-12-31" to "2026-01-01",
        "2026-02-01" to "2026-01-22"
    )
    
    val fmt = DateTimeFormatter.ISO_LOCAL_DATE
    val differences = datePairs.map { (first, second) ->
        val date1 = LocalDate.parse(first, fmt)
        val date2 = LocalDate.parse(second, fmt)
        ChronoUnit.DAYS.between(date1, date2)
    }
    
    println("Разности в днях: $differences")
}

fun task7() {
    val strings = listOf("math:Ivan", "bio:Olga", "math:Max", "bio:Ivan", "cs:Olga")
    
    val grouped = mutableMapOf<String, MutableList<String>>()
    strings.forEach { str ->
        val (subject, student) = str.split(":")
        grouped.getOrPut(subject) { mutableListOf() }.add(student)
    }
    
    println("Группировка по предметам: $grouped")
}

fun task8() {
    val strings = listOf(
        "Start at 2026/01/22 09:14",
        "No time here",
        "End: 22-01-2026 18:05"
    )
    
    val pattern1 = Regex("(\\d{4})/(\\d{2})/(\\d{2})\\s+(\\d{2}):(\\d{2})")
    val pattern2 = Regex("(\\d{2})-(\\d{2})-(\\d{4})\\s+(\\d{2}):(\\d{2})")
    
    val results = strings.mapNotNull { str ->
        pattern1.find(str)?.let { match ->
            val (year, month, day, hour, minute) = match.destructured
            "$year-$month-$day $hour:$minute"
        } ?: pattern2.find(str)?.let { match ->
            val (day, month, year, hour, minute) = match.destructured
            "$year-$month-$day $hour:$minute"
        }
    }
    
    println("Извлеченные даты и время: $results")
}
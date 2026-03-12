package ru.tbank.education.school.lesson1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.tbank.education.school.lesson1.examples.AppInfoProperties

@SpringBootApplication
@EnableConfigurationProperties(AppInfoProperties::class)
class Lesson1Application

fun main(args: Array<String>) {
	runApplication<Lesson1Application>(*args)
}

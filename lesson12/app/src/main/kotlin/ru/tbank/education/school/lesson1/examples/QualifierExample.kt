package ru.tbank.education.school.lesson1.examples

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.BeanNameAware
import org.springframework.stereotype.Component

@Component // Мы явно задали имя бина
class QualifierExample : BeanNameAware {

    private val log = LoggerFactory.getLogger(javaClass)
    private var nameInSpring: String? = null

    // Этот метод вызовет сам Spring на этапе инициализации
    override fun setBeanName(name: String) {
        this.nameInSpring = name
        log.info("Бин узнал, что его зовут: $name")
        // Выведет: "Бин узнал, что его зовут: myCustomServiceName"
    }

    fun doWork() {
        log.info("Работает бин с именем $nameInSpring")
    }
}
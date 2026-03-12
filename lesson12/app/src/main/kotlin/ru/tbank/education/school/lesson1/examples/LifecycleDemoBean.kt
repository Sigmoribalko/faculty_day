package ru.tbank.education.school.lesson1.examples

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.BeanNameAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
class LifecycleDemoBean : BeanNameAware, CommandLineRunner {

    private var beanName: String? = null
    private var dependency: String? = null

    // 1. Конструктор (в Kotlin это основной конструктор или блок init)
    init {
        println("1. Init block: Объект создан. Зависимостей еще нет, поля не проинициализированы.")
    }

    // 2. Внедрение через сеттер
    @Autowired
    fun setDependency(dep: String) { // Допустим, внедряем бин-строку
        this.dependency = dep
        println("2. Setter Injection: Зависимость [$dep] внедрена.")
    }

    // 3. Aware интерфейсы
    override fun setBeanName(name: String) {
        this.beanName = name
        println("3. BeanNameAware: Бин получил имя в контексте: $name")
    }

    // 4. Инициализация
    @PostConstruct
    fun postConstruct() {
        println("4. @PostConstruct: Бин полностью настроен и готов к работе.")
    }

    // 5. Точка входа в приложение
    override fun run(vararg args: String?) {
        println("5. CommandLineRunner: Весь контекст Spring загружен! Выполняю логику...")
        println("   Работаю с зависимостью: $dependency")
    }

    // 6. Уничтожение
    @PreDestroy
    fun preDestroy() {
        println("6. @PreDestroy: Приложение останавливается. Очищаю ресурсы для бина $beanName.")
    }
}

@Configuration
class MyConfiguration {
    @Bean
    @Primary
    fun depName(): String = "DEPENDENCY"

    @Bean
    fun depName2(): String = "DEPENDENCY1"
}
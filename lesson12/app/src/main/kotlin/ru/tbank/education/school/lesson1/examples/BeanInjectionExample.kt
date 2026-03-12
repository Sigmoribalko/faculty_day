package ru.tbank.education.school.lesson1.examples


import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
class MyBean(
    val myLittleBean: MyLittleBean
) : CommandLineRunner {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        log.info("Бин ${myLittleBean.name} попал в контекст")
    }
}

@Component
class MyLittleBean(
    val name: String
)

@Configuration
class MyBeanConfiguration(
    val string: String,
) {

    @Bean
    fun myLittleBean1() = MyLittleBean("David")

    @Bean
    fun myLittleBean2() = MyLittleBean("Eric")

    @Bean
    fun myLittleBean3() = MyLittleBean("Alex")

    @Bean
    fun createMyBean(
        @Qualifier("myLittleBean")
        myLittleBean: MyLittleBean
    ) = MyBean(myLittleBean)
}
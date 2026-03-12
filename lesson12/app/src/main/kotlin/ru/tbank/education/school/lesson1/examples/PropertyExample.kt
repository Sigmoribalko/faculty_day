package ru.tbank.education.school.lesson1.examples

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.web.bind.annotation.*

// 1. Модель конфигурации
@ConfigurationProperties(prefix = "app.info")
data class AppInfoProperties(
    val name: String,
    val version: String,
    val features: List<String>
)

// 2. REST-контроллер
@RestController
@RequestMapping("/api/status")
class StatusController(
    private val appInfo: AppInfoProperties // Инъекция настроек
) {

    @GetMapping
    fun getStatus() = mapOf(
        "service" to appInfo.name,
        "ver" to appInfo.version,
        "active_features" to appInfo.features,
        "timestamp" to System.currentTimeMillis()
    )
}
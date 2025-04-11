package org.example.ktor

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.ApplicationConfig
import org.jetbrains.exposed.sql.Database

fun initDatabase(config: ApplicationConfig) {

    val driverClassName = config.property("db.driverClassName").getString()
    val jdbcUrl = config.property("db.jdbcUrl").getString()
    val username = config.property("db.username").getString()
    val password = config.property("db.password").getString()
    val maximumPoolSize = config.property("db.maximumPoolSize").getString().toInt()

    val hikariConfig = HikariConfig().apply {
        this.driverClassName = driverClassName
        this.jdbcUrl = jdbcUrl
        this.username = username
        this.password = password
        this.maximumPoolSize = maximumPoolSize
    }

    val dataSource = HikariDataSource(hikariConfig)
    Database.connect(dataSource)
}
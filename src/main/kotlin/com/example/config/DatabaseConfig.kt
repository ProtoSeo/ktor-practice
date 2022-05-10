package com.example.config

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase() {
    val databaseObject = environment.config.config("ktor.database")
    val driver = databaseObject.property("driver").getString()
    val url = databaseObject.property("url").getString()
    val user = databaseObject.property("user").getString()
    val password = databaseObject.property("password").getString()
    Database.connect(url, driver, user, password)
}
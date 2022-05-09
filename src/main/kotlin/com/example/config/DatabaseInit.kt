package com.example.config

import org.jetbrains.exposed.sql.Database
import org.slf4j.LoggerFactory

object DatabaseInit {
    fun init() {
        Database.connect(
            url = "jdbc:mysql://localhost:3306/ktor",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "user",
            password = "mypass"
        )
        LoggerFactory.getLogger("DatabaseInit").info("Database Init!!!")
    }
}

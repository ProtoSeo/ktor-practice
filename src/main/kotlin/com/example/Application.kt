package com.example

import com.example.config.configureDatabase
import com.example.config.configureRouting
import com.example.config.configureTemplate
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureDatabase()
    configureRouting()
    configureTemplate()
}
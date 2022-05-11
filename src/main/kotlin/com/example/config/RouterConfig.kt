package com.example.config

import com.example.item.controller.ItemController
import com.example.item.route.itemRoute
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import org.kodein.di.instance

fun Application.configureRouting() {
    val itemController: ItemController by ModuleConfig.kodein.instance()

    routing {
        indexRoute()
        itemRoute(itemController)
    }
}

fun Routing.indexRoute() {
    route("/") {
        get {
            call.respond(ThymeleafContent("index", mapOf("host" to call.request.host())))
        }
    }
}
package com.example.item.route

import com.example.item.controller.ItemController
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*

fun Route.itemRoute(itemController: ItemController) {
    route("items") {
        get {
            val items = itemController.getItems(call)
            call.respond(ThymeleafContent("itemList", mapOf("items" to items)))
        }
        get("new") {
        }
        post {
        }
        get("{id}") {
        }
        get("{id}/edit") {
        }
        post("{id}") {
        }
    }
}
package com.example.item.route

import com.example.item.controller.ItemController
import io.ktor.server.routing.*

fun Route.itemRoute(itemController: ItemController) {
    route("items") {
        get {
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
package com.example.item.route

import com.example.item.controller.ItemController
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*

fun Routing.itemRoute(itemController: ItemController) {
    route("items") {
        createItem(itemController)
        readItem(itemController)
        updateItem(itemController)
        deleteItem(itemController)
    }
}

private fun Route.createItem(itemController: ItemController) {
    get("new") {
        call.respond(ThymeleafContent("items/newItem", mapOf()))
    }
    post("new") {
        call.respondRedirect(itemController.saveItem(call))
    }
    post("new/random") {
        call.respondRedirect(itemController.saveRandomItems())
    }
}

private fun Route.readItem(itemController: ItemController) {
    get {
        val items = itemController.getItems(call)
        call.respond(ThymeleafContent("items/itemList", mapOf("items" to items)))
    }
}

private fun Route.updateItem(itemController: ItemController) {
    get("{id}/edit") {
        val bookEditForm = itemController.getUpdateItemForm(call)
        call.respond(ThymeleafContent("items/editItem", mapOf("form" to bookEditForm)))
    }
    post("{id}/edit") {
        call.respondRedirect(itemController.updateItem(call))
    }
}

private fun Route.deleteItem(itemController: ItemController) {
    post("{id}/delete") {
        call.respondRedirect(itemController.deleteItem(call))
    }
    post("delete") {
        call.respondRedirect(itemController.deleteAllItem())
    }
}
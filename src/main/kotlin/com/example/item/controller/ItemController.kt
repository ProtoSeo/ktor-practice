package com.example.item.controller

import com.example.item.dto.ItemRequestDto
import com.example.item.dto.ItemResponseDto
import com.example.item.service.ItemService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.util.*

class ItemController(private val itemService: ItemService) {

    suspend fun getItem(call: ApplicationCall) {
        val id = call.parameters.getOrFail<Long>("id")
        val item = itemService.getItem(id)

    }

    suspend fun getItems(call: ApplicationCall): List<ItemResponseDto> {
        return itemService.getItems()
    }

    suspend fun saveItem(call: ApplicationCall): String {
        val formParameters = call.receiveParameters()
        val name = formParameters.getOrFail("name")
        val price = formParameters.getOrFail<Int>("price")
        val stockQuantity = formParameters.getOrFail<Int>("stockQuantity")
        val newItemId = itemService.saveItem(ItemRequestDto(name, price, stockQuantity))
        return "/items"
    }

    suspend fun saveRandomItems(): String {
        itemService.saveRandomItems()
        return "/items"
    }

    suspend fun getUpdateItemForm(call: ApplicationCall): Map<String, Any> {
        val id = call.parameters.getOrFail<Long>("id")
        val item = itemService.getItem(id)
        return mapOf(
            "id" to id,
            "name" to item.name,
            "price" to item.price,
            "stockQuantity" to item.stockQuantity
        )
    }

    suspend fun updateItem(call: ApplicationCall): String {
        val id = call.parameters.getOrFail<Long>("id")
        val formParameters = call.receiveParameters()
        val name = formParameters.getOrFail("name")
        val price = formParameters.getOrFail<Int>("price")
        val stockQuantity = formParameters.getOrFail<Int>("stockQuantity")
        val updateItemId = itemService.updateItem(id, ItemRequestDto(name, price, stockQuantity))
        return "/items"
    }

    suspend fun deleteItem(call: ApplicationCall): String {
        val id = call.parameters.getOrFail<Long>("id")
        itemService.delete(id)
        return "/items"
    }

    suspend fun deleteAllItem(): String {
        itemService.deleteAllItem()
        return "/items"
    }
}
package com.example.item.controller

import com.example.item.dto.ItemRequestDto
import com.example.item.service.ItemService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.util.*

class ItemController(private val itemService: ItemService) {

    suspend fun getItem(call: ApplicationCall) {
        val id = call.parameters.getOrFail<Long>("id")
        val item = itemService.getItem(id)

    }

    suspend fun getItems(call: ApplicationCall) {
        val items = itemService.getItems()
    }

    suspend fun saveItem(call: ApplicationCall) {
        val formParameters = call.receiveParameters()
        val name = formParameters.getOrFail("name")
        val price = formParameters.getOrFail<Int>("price")
        val stockQuantity = formParameters.getOrFail<Int>("quantity")
        val newItemId = itemService.saveItem(ItemRequestDto(name, price, stockQuantity))

    }

    suspend fun updateItem(call: ApplicationCall) {
        val id = call.parameters.getOrFail<Long>("id")
        val formParameters = call.receiveParameters()
        val name = formParameters.getOrFail("name")
        val price = formParameters.getOrFail<Int>("price")
        val stockQuantity = formParameters.getOrFail<Int>("quantity")
        val updateItemId = itemService.updateItem(id, ItemRequestDto(name, price, stockQuantity))

    }
}
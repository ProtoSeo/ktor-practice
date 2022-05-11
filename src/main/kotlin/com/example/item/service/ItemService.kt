package com.example.item.service

import com.example.item.domain.ItemRepository
import com.example.item.dto.ItemRequestDto
import com.example.item.dto.ItemResponseDto
import java.util.Random

class ItemService(private val itemRepository: ItemRepository, private val random: Random) {

    private val charPool: List<Char> = ('A'..'Z') + ('0'..'9')

    fun saveItem(itemRequestDto: ItemRequestDto): Long {
        return itemRepository.save(itemRequestDto)
    }

    fun saveRandomItems() {
        val randomItems = (1..10).map {
            val randomName = createRandomString()
            val price = ((random.nextInt(20) + 1) * 100)
            val stockQuantity = ((random.nextInt(10) + 1) * 10)
            ItemRequestDto(randomName, price, stockQuantity)
        }.toList()

        itemRepository.saveRandomItems(randomItems)
    }

    private fun createRandomString() = (1..8)
        .map { random.nextInt(charPool.size) }
        .map(charPool::get)
        .joinToString("")


    fun updateItem(id: Long, itemRequestDto: ItemRequestDto): ItemResponseDto {
        return itemRepository.update(id, itemRequestDto)
    }

    fun getItem(id: Long): ItemResponseDto {
        return itemRepository.findById(id)
    }

    fun getItems(): List<ItemResponseDto> {
        return itemRepository.findAll()
    }

    fun delete(id: Long) {
        itemRepository.delete(id)
    }

    fun deleteAllItem() {
        itemRepository.clear()
    }
}
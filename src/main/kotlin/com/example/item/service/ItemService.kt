package com.example.item.service

import com.example.item.domain.ItemRepository
import com.example.item.dto.ItemRequestDto
import com.example.item.dto.ItemResponseDto

class ItemService(private val itemRepository: ItemRepository) {
    fun saveItem(itemRequestDto: ItemRequestDto): Long {
        return itemRepository.save(itemRequestDto)
    }

    fun updateItem(id: Long, itemRequestDto: ItemRequestDto): ItemResponseDto {
        return itemRepository.update(id, itemRequestDto)
    }

    fun getItem(id: Long): ItemResponseDto {
        return itemRepository.findById(id)
    }

    fun getItems(): List<ItemResponseDto> {
        return itemRepository.findAll()
    }
}
package com.example.item.domain

import com.example.item.dto.ItemRequestDto
import com.example.item.dto.ItemResponseDto
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ItemRepository {
    init {
        transaction {
            SchemaUtils.create(Items)
        }
    }

    fun findById(id: Long): ItemResponseDto {
        return transaction {
            Items.select { Items.id eq id }.map { row ->
                ItemResponseDto(
                    row[Items.id].value,
                    row[Items.name],
                    row[Items.price],
                    row[Items.stockQuantity]
                )
            }.first()
        }
    }

    fun findAll(): List<ItemResponseDto> {
        return transaction {
            Items.selectAll().map { row ->
                ItemResponseDto(
                    row[Items.id].value,
                    row[Items.name],
                    row[Items.price],
                    row[Items.stockQuantity]
                )
            }.toList()
        }
    }

    fun save(itemDto: ItemRequestDto): Long {
        return transaction {
            Items.insertAndGetId { row ->
                row[name] = itemDto.name
                row[price] = itemDto.price
                row[stockQuantity] = itemDto.stockQuantity
            }.value
        }
    }

    fun saveRandomItems(randomItems: List<ItemRequestDto>) {
        transaction {
            Items.batchInsert(randomItems) { item ->
                this[Items.name] = item.name
                this[Items.price] = item.price
                this[Items.stockQuantity] = item.stockQuantity
            }
        }
    }

    fun update(id: Long, itemDto: ItemRequestDto): ItemResponseDto {
        transaction {
            Items.update({ Items.id eq id }) { row ->
                row[name] = itemDto.name
                row[price] = itemDto.price
                row[stockQuantity] = itemDto.stockQuantity
            }
        }
        return findById(id)
    }

    fun delete(id: Long) {
        transaction {
            Items.deleteWhere { Items.id eq id }
        }
    }

    fun clear() = transaction { Items.deleteAll() }

}
package com.example.item.domain

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

internal object Items : LongIdTable(name = "item") {
    val name: Column<String> = varchar("name", 50)
    val price: Column<Int> = integer("price")
    val stockQuantity: Column<Int> = integer("stock_quantity")
}
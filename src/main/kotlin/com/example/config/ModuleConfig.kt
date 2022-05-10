package com.example.config

import com.example.item.controller.ItemController
import com.example.item.domain.ItemRepository
import com.example.item.service.ItemService
import org.kodein.di.*

object ModuleConfig {
    private val itemModule = DI.Module("ITEM") {
        bindSingleton { ItemController(instance()) }
        bindSingleton { ItemService(instance()) }
        bindSingleton { ItemRepository() }
    }

    internal val kodein = DI {
        import(itemModule)
    }
}
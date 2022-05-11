package com.example.config

import com.example.item.controller.ItemController
import com.example.item.domain.ItemRepository
import com.example.item.service.ItemService
import org.kodein.di.*
import java.security.SecureRandom
import kotlin.random.Random

object ModuleConfig {
    private val itemModule = DI.Module("Item") {
        bindSingleton { ItemController(instance()) }
        bindSingleton { ItemService(instance(), instance()) }
        bindSingleton { ItemRepository() }
        bindSingleton { SecureRandom() }
    }

    internal val kodein = DI {
        import(itemModule)
    }
}
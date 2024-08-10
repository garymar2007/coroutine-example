package org.gary

import kotlinx.coroutines.delay

enum class Product(val description: String, val deliveryTime: Long) {
    DOORS("Doors", 450),
    WINDOWS("Windows", 1_200)
}

suspend fun order(item: Product) : Product {
    println("ORDER EN ROUTE  >>> The ${item.description} are on the way!")
    delay(item.deliveryTime)
    println("ORDER DELIVERED >>> Your ${item.description} have arrived.")
    return item
}
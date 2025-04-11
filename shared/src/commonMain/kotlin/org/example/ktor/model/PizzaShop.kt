package org.example.ktor.model

import kotlinx.serialization.Serializable
import kotlinx.rpc.RemoteService
import kotlinx.rpc.annotations.Rpc
import kotlin.coroutines.CoroutineContext

@Rpc
interface PizzaShop : RemoteService {
    suspend fun orderPizza(pizza: Pizza): Receipt
}

class PizzaShopImpl(
    override val coroutineContext: CoroutineContext
) : PizzaShop {
    override suspend fun orderPizza(pizza: Pizza): Receipt {
        return Receipt(7.89)
    }
}

@Serializable
class Pizza(val name: String)

@Serializable
class Receipt(val amount: Double)
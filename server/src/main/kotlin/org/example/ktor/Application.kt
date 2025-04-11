package org.example.ktor

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.rpc.krpc.ktor.server.Krpc
import kotlinx.rpc.krpc.serialization.json.json
import kotlinx.rpc.krpc.ktor.server.rpc
import org.example.ktor.model.Pizza
import org.example.ktor.model.PizzaShop
import org.example.ktor.model.Receipt
import kotlin.coroutines.CoroutineContext

class PizzaShopImpl(
    override val coroutineContext: CoroutineContext
) : PizzaShop {
    private val openOrders = mutableMapOf<String, MutableList<Pizza>>()

    override suspend fun orderPizza(clientID: String, pizza: Pizza): Receipt {
        if(openOrders.containsKey(clientID)) {
            openOrders[clientID]?.add(pizza)
        } else {
            openOrders[clientID] = mutableListOf(pizza)
        }
        return Receipt(3.45)
    }

    override suspend fun viewOrders(clientID: String): Flow<Pizza> {
        val orders = openOrders[clientID]
        if (orders != null) {
            return flow {
                for (order in orders) {
                    emit(order)
                    delay(1000)
                }
            }
        }
        return flow {}
    }
}

fun main() {
    embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = "0.0.0.0",
        module = Application::module
    )
        .start(wait = true)
}

fun Application.module() {
    install(Krpc)

    routing {
        rpc("/pizza") {
            rpcConfig {
                serialization {
                    json()
                }
            }

            registerService<PizzaShop> { ctx -> PizzaShopImpl(ctx) }
        }
    }
/*
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
    }
    */
}

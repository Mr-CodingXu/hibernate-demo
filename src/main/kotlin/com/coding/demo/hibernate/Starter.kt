package com.coding.demo.hibernate

import com.coding.demo.hibernate.provider.MyProvider
import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.await
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val LOG: Logger = LoggerFactory.getLogger("com.coding.demo.hibernate.Starter")

suspend fun main() {
    val vertx = Vertx.vertx()
    MyProvider.vert = vertx
    vertx
        .deployVerticle("com.coding.demo.hibernate.MainVerticle")
        .await()
}

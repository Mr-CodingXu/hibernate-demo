package com.coding.demo.hibernate.provider

import io.vertx.core.Vertx
import org.hibernate.reactive.vertx.VertxInstance

class MyProvider: VertxInstance {

    companion object {
        @JvmField
        var vert: Vertx? = null
    }

    override fun getVertx(): Vertx? = vert
}

package com.coding.demo.hibernate

import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import org.hibernate.SessionFactory
import org.hibernate.engine.spi.SessionFactoryImplementor
import org.hibernate.reactive.stage.Stage
import org.hibernate.reactive.vertx.VertxInstance
import java.util.*
import javax.persistence.Persistence

class MainVerticle : CoroutineVerticle() {

    override suspend fun start() {
        val sessionFactoryAwait = vertx.executeBlocking<Stage.SessionFactory> {
            // Get SessionFactory
            val sf: SessionFactory =
                Persistence
                    .createEntityManagerFactory("mysql-demo")
                    .unwrap(SessionFactory::class.java)
            // Get VertxInstance via docs
            val vertxViaDoc: VertxInstance =
                sf.unwrap(SessionFactoryImplementor::class.java)
                    .serviceRegistry
                    .getService(VertxInstance::class.java)
            // Get VertxInstance via Service Loader
            val loaderViaSpi: ServiceLoader<VertxInstance> = ServiceLoader.load(VertxInstance::class.java)
            val vertxViaSpi: VertxInstance = loaderViaSpi.single()

            println(vertxViaDoc.javaClass.simpleName)
            println(vertxViaSpi.javaClass.simpleName)

            val stageSf = sf.unwrap(Stage.SessionFactory::class.java)
            it.complete(stageSf)
        }

        val sessionFactory: Stage.SessionFactory = sessionFactoryAwait.await()

        vertx
            .createHttpServer()
            .requestHandler { req ->
                req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!")
            }
            .listen(8888)
            .await()

        println("HTTP server started on port 8888")
    }
}

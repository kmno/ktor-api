package com.kamran

import com.kamran.config.AppConfig
import com.kamran.config.setupConfig
import com.kamran.database.DatabaseFactory
import com.kamran.di.appModule
import com.kamran.plugins.*
import freemarker.cache.ClassTemplateLoader
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.locations.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.module.Module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalLocationsAPI
@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.module(testing: Boolean = true, koinModules: List<Module> = listOf(appModule)) {

    install(Koin) {
        slf4jLogger()
        modules(koinModules)
    }

    setupConfig()

    val appConfig by inject<AppConfig>()

    if (!appConfig.serverConfig.isProd) {
       val root = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        root.atLevel(Level.TRACE)
      //  root.level = ch.qos.logback.classic.Level.TRACE
    }

    install(CallLogging) {
        level = Level.INFO
    }

    DatabaseFactory.init()
    configureTemplating()
    configAPI()
    configAuth()
    configureSerialization()
    configureRouting()
    install(Locations)

    routing {
        //jokeEndpoint()
        get("/") {
            call.respondText("This is a sample Ktor backend to get Chuck Norris jokes")
        }
    }
}

/* @Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
 fun Application.module() {

     // configureDbVariables()
     configAPI()
     configAuth()
     configureSerialization()
     configureRouting()
 }*/

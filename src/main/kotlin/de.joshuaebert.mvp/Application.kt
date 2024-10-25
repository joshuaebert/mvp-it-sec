package de.joshuaebert.mvp

import de.joshuaebert.mvp.routes.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.logging.*
import java.io.File

const val INDEX_LOCATION = "static"
internal val LOGGER = KtorSimpleLogger("MVP")
internal lateinit var config: Config

data class Config(val location: String)

private fun createFile(location: String) = runCatching {
    val indexFile = File("$location/index.html")
    if(indexFile.exists()) {
        return@runCatching
    }

    LOGGER.info("index.html does not exist, creating default...")

    val dirCreateResult = indexFile.parentFile.mkdirs()

    if(!dirCreateResult && !indexFile.parentFile.exists()) {
        LOGGER.error("Could not create parent directory for index.html")
        return@runCatching
    }

    val fileCreationResult = indexFile.createNewFile()

    if(!fileCreationResult) {
        LOGGER.error("Could not create index.html because it already exists")
        return@runCatching
    }

    object{}.javaClass.getResourceAsStream("../../../default/index.html")?.use { inputStream ->
        indexFile.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }
    LOGGER.info("index.html created successfully")
}

fun main(args: Array<String>) {
    config = Config(if(args.isEmpty()) INDEX_LOCATION else args[0])

    val result = createFile(config!!.location)
    if(result.isFailure) {
        LOGGER.error("Could not create index.html: ${result.exceptionOrNull()}")
        return
    }

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
}

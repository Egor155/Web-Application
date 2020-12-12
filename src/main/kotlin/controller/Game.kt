package controller

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.thymeleaf.*
import model.Game
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import java.io.File

fun main() {
    val server = embeddedServer(Netty, port = 8080) {

        routing {

            install(Thymeleaf) {
                setTemplateResolver(
                        ClassLoaderTemplateResolver().apply {
                            prefix = "game/"
                            suffix = ".html"
                            characterEncoding = "utf-8"
                        }
                )
            }

            get("/") {
                call.respondFile(File("./src/main/resources/pages/game.html"))
                //  call.respond(ThymeleafContent("question2", mapOf("question" to question2)))
            }

            get("/start") {

                val game = Game(5, 5)
                game.area
                call.respond(ThymeleafContent("gan", mapOf("game" to game)))
            }
        }
    }
    server.start(wait = true)
}

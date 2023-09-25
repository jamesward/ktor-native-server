import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

expect fun reportMemory()

fun main() {
    reportMemory()

    val server = embeddedServer(CIO, port = 8080) {
        routing {
            get("/") {
                call.respondText("hello, world")
            }
        }
    }

    server.start(wait = true)
}

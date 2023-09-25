import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

actual fun reportMemory() {
    fun printMemory() {
        println("Total: " + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "MB")
        println("Free: " + (Runtime.getRuntime().freeMemory() / 1024 / 1024) + "MB")
    }

    CoroutineScope(Dispatchers.IO).launch {
        while (true) {
            println("--- Pre-GC ---")
            printMemory()
            Runtime.getRuntime().gc()
            println("--- Post-GC ---")
            printMemory()
            delay(5.seconds)
        }
    }
}
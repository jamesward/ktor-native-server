import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.native.runtime.GC
import kotlin.native.runtime.NativeRuntimeApi
import kotlin.time.Duration.Companion.seconds

@OptIn(NativeRuntimeApi::class)
actual fun reportMemory() {
    fun printMemory() {
        println("targetHeapBytes: " + (GC.targetHeapBytes / 1024 / 1024) + "MB")
    }

    CoroutineScope(Dispatchers.IO).launch {
        while (true) {
            println("--- Pre-GC ---")
            printMemory()
            GC.collect()
            println("--- Post-GC ---")
            printMemory()
            delay(5.seconds)
        }
    }
}
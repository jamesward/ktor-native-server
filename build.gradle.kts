plugins {
    kotlin("multiplatform") version "1.9.20-Beta2"
}

repositories {
    mavenCentral()
}

kotlin {
    linuxX64 {
        binaries {
            executable(listOf(DEBUG, RELEASE)) {
                entryPoint = "main"
            }
        }
    }

    jvm {
        @Suppress("OPT_IN_USAGE")
        mainRun {
            mainClass = "MainKt"
        }
    }

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation("io.ktor:ktor-server-core:2.3.4")
                implementation("io.ktor:ktor-server-cio:2.3.4")
            }
        }

        getByName("jvmMain") {
            dependencies {
                runtimeOnly("org.slf4j:slf4j-simple:2.0.9")
            }
        }
    }
}

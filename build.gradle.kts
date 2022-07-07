plugins {
    kotlin("multiplatform") version "1.7.0"
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

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(kotlin("reflect"))
                implementation("io.ktor:ktor-server-core:2.0.3")
                implementation("io.ktor:ktor-server-cio:2.0.3")
                implementation("io.ktor:ktor-client-core:2.0.3")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3-native-mt") {
                    version {
                        strictly("1.6.3-native-mt")
                    }
                }
            }
        }
    }
}

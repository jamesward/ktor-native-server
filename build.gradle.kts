import com.google.cloud.tools.jib.gradle.JibTask

buildscript {
    dependencies {
        classpath("com.google.cloud.tools:jib-native-image-extension-gradle:0.1.0")
    }
}


plugins {
    kotlin("multiplatform") version "2.0.21"
    id("com.google.cloud.tools.jib") version "3.4.3"
}

repositories {
    mavenCentral()
}

kotlin {
    linuxX64 {
        binaries {
            executable(listOf(DEBUG, RELEASE)) {
                entryPoint = "main"
                linkerOpts("--as-needed")
                freeCompilerArgs += "-Xoverride-konan-properties=linkerGccFlags.linux_x64=-lgcc -lgcc_eh -lc"
            }
        }
    }

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation("io.ktor:ktor-server-core:3.0.0")
                implementation("io.ktor:ktor-server-cio:3.0.0")
            }
        }
    }
}

tasks.register<Copy>("copyBinary") {
    dependsOn(tasks.first { it.name.contains("linkReleaseExecutable") })
    from(layout.buildDirectory.file("bin/linuxX64/releaseExecutable/ktor-native-server.kexe"))
    into(layout.buildDirectory.dir("native/nativeCompile"))
}

tasks.withType<JibTask> {
    dependsOn("copyBinary")
}

jib {
    from {
        image = "gcr.io/distroless/base"
    }
    pluginExtensions {
        pluginExtension {
            implementation = "com.google.cloud.tools.jib.gradle.extension.nativeimage.JibNativeImageExtension"
            properties = mapOf(Pair("imageName", "ktor-native-server.kexe"))
        }
    }
    container {
        mainClass = "MainKt"
    }
}

sourceSets.create("main")

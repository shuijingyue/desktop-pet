import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.code.cube.pet"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    // https://mvnrepository.com/artifact/org.jetbrains.compose.components/components-animatedimage-desktop
    implementation("org.jetbrains.compose.components:components-animatedimage-desktop:1.5.11")

}

compose.desktop {
    application {
        mainClass = "com.code.cube.pet.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.code.cube.pet"
            packageVersion = "1.0.0"
            macOS {
                iconFile.set(project.file("assets/app_icon.icns"))
            }
            windows {
                iconFile.set(project.file("assets/app_icon.ico"))
            }
            linux {
                iconFile.set(project.file("assets/app_icon.png"))
            }
        }
    }
}

import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.application")version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.21" apply false
    id("com.google.devtools.ksp") version "1.8.0-1.0.8" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint.apply {
        android.value(true)
        ignoreFailures.value(false)
        debug.value(false)
        disabledRules.addAll("import-ordering")
        reporters {
            reporter(PLAIN)
            reporter(CHECKSTYLE)
            reporter(SARIF)
        }
    }
}

detekt {
    toolVersion = "1.22.0"
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

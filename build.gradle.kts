// Top‑level build file where you can add configuration options common to all sub‑projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android)       apply false
    alias(libs.plugins.kotlin.compose)       apply false
    alias(libs.plugins.android.library)      apply false
}

/*
 * Work‑around for:
 *   Unable to find method 'java.lang.String com.squareup.javapoet.ClassName.canonicalName()'
 * caused by a version mismatch inside Gradle’s dependency graph.
 *
 * We pin every classpath and project configuration to a consistent JavaPoet version that
 * definitely provides the missing method (1.13.0).
 */
buildscript {
    configurations.all {
        resolutionStrategy {
            force("com.squareup:javapoet:1.13.0")
        }
    }
}

subprojects {
    configurations.all {
        resolutionStrategy {
            force("com.squareup:javapoet:1.13.0")
        }
    }
}
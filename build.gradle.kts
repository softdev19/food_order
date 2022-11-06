import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

ext {
    extra["composeVersion"] = "1.2.1"
    extra["ktorVersion"] = "2.1.2"
}

plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.10" apply false
}

allprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

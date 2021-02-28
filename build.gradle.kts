buildscript {
    val kotlinVersion = "1.4.31"

    rootProject.extra["composeVersion"] = "1.0.0-beta01"
    rootProject.extra["coroutinesVersion"] = "1.4.2"
    rootProject.extra["ktorVersion"] = "1.5.2"
    rootProject.extra["serializationVersion"] = "1.1.0"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha08")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
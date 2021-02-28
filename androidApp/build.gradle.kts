plugins {
    id("com.android.application")
    id("kotlin-android")
}

val composeVersion: String by rootProject.extra

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.kuuuurt.spacex.compose"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
}

dependencies {
    implementation(project(":shared"))

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")

    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.0")
    implementation("androidx.activity:activity-compose:1.3.0-alpha03")
    implementation("androidx.navigation:navigation-compose:1.0.0-alpha08")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha02")

    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.6.1")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.0-beta01")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
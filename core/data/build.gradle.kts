plugins {
    id("convention.android.library")
    id("convention.android.hilt")
}

android {
    namespace = "karim.gabbasov.data"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        with(com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)) {
            buildConfigField("String", "DADATA_API_KEY", getProperty("DADATA_API_KEY"))
        }
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(libs.locationService)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit4Ext)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.robolectric)
}

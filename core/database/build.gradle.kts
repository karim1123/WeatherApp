plugins {
    id("convention.android.library")
    id("convention.android.hilt")
    id("convention.android.room")
}

android {
    namespace = "karim.gabbasov.database"

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }
}

dependencies {
    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit4Ext)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.robolectric)
}

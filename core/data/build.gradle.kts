plugins {
    id("convention.android.library")
    id("convention.android.hilt")
}

android {
    namespace = "karim.gabbasov.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:model"))

    implementation(libs.sandwich)

    implementation(libs.locationService)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit4Ext)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.robolectric)
}

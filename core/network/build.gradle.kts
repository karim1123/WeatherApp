plugins {
    id("convention.android.library")
    id("convention.android.hilt")
}

android {
    namespace = "karim.gabbasov.network"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.interceptor)
    api(libs.sandwich)
}

plugins {
    id("convention.android.feature")
}

android {
    namespace = "karim.gabbasov.forecast"
}

dependencies {
    implementation(libs.accompanistPermissions)
}

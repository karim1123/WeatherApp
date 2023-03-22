plugins {
    id("convention.android.library")
    id("convention.android.compose")
}

android {
    namespace = "karim.gabbasov.designsystem"
}

dependencies {
    implementation(libs.composeMaterial3)
}

plugins {
    id("convention.android.library")
}

android {
    namespace = "karim.gabbasov.feature_api"
}

dependencies {
    implementation(libs.hiltNavigationCompose)
}

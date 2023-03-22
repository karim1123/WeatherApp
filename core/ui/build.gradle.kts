plugins {
    id("convention.android.library")
    id("convention.android.compose")
    id("convention.android.hilt")
}

android {
    namespace = "karim.gabbasov.ui"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))

    implementation(libs.composeUi)
    implementation(libs.composeMaterial3)
    implementation(libs.composeUiTooling)
    implementation(libs.accompanistPermissions)

    implementation(libs.hiltNavigationCompose)
}

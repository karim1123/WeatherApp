plugins {
    id("convention.android.application")
    id("convention.android.compose")
    id("convention.android.hilt")
}

android {
    namespace = "karim.gabbasov.weatherapp"

    defaultConfig {
        multiDexEnabled = false
        applicationId = "karim.gabbasov.weatherapp"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
        }
    }

    packagingOptions {
        resources {
            exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:featureapi"))
    implementation(project(":core:designsystem"))

    implementation(project(":feature:forecast"))
    implementation(project(":feature:detailed-forecast"))

    implementation(libs.composeUi)
    implementation(libs.composeMaterial3)
    implementation(libs.composeUiTooling)
    implementation(libs.composeActivity)
    implementation(libs.composeLifecycleViewModel)
    implementation(libs.composeNavigation)

    implementation(libs.hiltNavigationCompose)
}

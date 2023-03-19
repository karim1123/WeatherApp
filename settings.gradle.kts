pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "WeatherApp"

include(":app")
include(":core:model")
include(":core:common")
include(":core:network")
include(":core:database")
include(":core:data")
include(":core:featureapi")
include(":core:designsystem")
include(":core:ui")

include(":feature:forecast")
include(":feature:detailed-forecast")

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("convention.android.library")
                apply("convention.android.hilt")
                apply("convention.android.compose")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":core:model"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:common"))
                add("implementation", project(":core:network"))
                add("implementation", project(":core:featureapi"))

                add("implementation", libs.findLibrary("composeUi").get())
                add("implementation", libs.findLibrary("composeMaterial3").get())
                add("implementation", libs.findLibrary("composeUiTooling").get())
                add("implementation", libs.findLibrary("composeRuntime").get())
                add("implementation", libs.findLibrary("composeLifecycleViewModel").get())
                add("implementation", libs.findLibrary("immutableCollections").get())

                add("debugImplementation", libs.findLibrary("composeTestingUiTooling").get())
            }
        }
    }
}

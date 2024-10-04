pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Idea Collector"
include(":app")
include(":modules:core:core_api")
include(":modules:core:core_impl")
include(":modules:core:core_factory")
include(":modules:base:ui_kit")
include(":modules:features:home_screen:home_screen_api")
include(":modules:features:home_screen:home_screen_impl")
include(":modules:features:settings:settings_api")
include(":modules:features:settings:settings_impl")
include(":modules:base:infrastructure")

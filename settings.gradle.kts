rootProject.name = "devenv"

pluginManagement {
  repositories {
    maven(url = "http://home.gohla.nl:8091/artifactory/all/")
    gradlePluginPortal()
  }
}

// Add all subdirectories with a settings.gradle(.kts) file as an included build.
rootDir.list().forEach {
  if(File(rootDir, "$it/${Settings.DEFAULT_SETTINGS_FILE}").isFile || File(rootDir, "$it/${Settings.DEFAULT_SETTINGS_FILE}.kts").isFile) {
    includeBuild(it)
  }
}

// HACK: Temporarily include builds from spoofax.gradle, because IntelliJ does not support nested composite builds yet.
if(File(rootDir, "spoofax.gradle/example/langspecs/calc").isDirectory) {
  includeBuild("spoofax.gradle/example/langspecs/calc")
}
if(File(rootDir, "spoofax.gradle/example/langspecs/calc.lib").isDirectory) {
  includeBuild("spoofax.gradle/example/langspecs/calc.lib")
}

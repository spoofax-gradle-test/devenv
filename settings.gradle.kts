rootProject.name = "devenv"

pluginManagement {
  repositories {
    maven(url = "http://home.gohla.nl:8091/artifactory/all/")
    gradlePluginPortal()
  }
}

buildscript {
  repositories {
    maven(url = "http://home.gohla.nl:8091/artifactory/all/")
  }
  dependencies {
    classpath("org.eclipse.jgit:org.eclipse.jgit:5.2.0.201812061821-r")
  }
}

data class Repo(val name: String, val url: String, val branch: String)

fun repo(name: String) = Repo(name, "git@github.com:spoofax-gradle-test/$name.git", "develop")
val repos = listOf(
  // Gradle plugins.
  repo("gradle.config"),
  repo("gitonium"),
  repo("coronium"),
  // Libraries and applications.
  repo("log"),
  repo("pie"),
  repo("spoofax"),
  repo("spoofax.eclipse"),
  // Continuous integration.
  repo("jenkins.pipeline")
)

// Add all subdirectories with a settings.gradle(.kts) file as an included build.
rootDir.list().forEach {
  if(File(rootDir, "$it/${Settings.DEFAULT_SETTINGS_FILE}").isFile || File(rootDir, "$it/${Settings.DEFAULT_SETTINGS_FILE}.kts").isFile) {
    includeBuild(it)
  }
}

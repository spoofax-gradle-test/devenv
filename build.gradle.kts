plugins {
  id("org.metaborg.gradle.config.devenv") version "0.5.0"
}

devenv {
  repoUrlPrefix = "git@github.com:spoofax-gradle-test"

  // Gradle plugins.
  registerRepo("gradle.config")
  registerRepo("gitonium")
  registerRepo("coronium")
  registerRepo("spoofax.gradle")

  // Libraries and applications.
  registerRepo("log")
  registerRepo("pie")
  registerRepo("spoofax")
  registerRepo("spoofax.eclipse")
  
  // Continuous integration.
  registerRepo("jenkins.pipeline")
}

devenv {
  registerCompositeBuildTask("cleanAll", "Deletes the build directory for all projects in the composite build.")
  registerCompositeBuildTask("checkAll", "Runs all checks for all projects in the composite build.")
  registerCompositeBuildTask("assembleAll", "Assembles the outputs for all projects in the composite build.")
  registerCompositeBuildTask("buildAll", "Assembles and tests all projects in the composite build.")
  registerCompositeBuildTask("publishAll", "Publishes all publications produced by all projects in the composite build.")
}

tasks {
  register("runSpoofaxCli") {
    group = "application"
    description = "Runs 'spoofax.cli'."
    dependsOn(gradle.includedBuild("spoofax").task(":spoofax.cli:run"))
  }
  register("runSpoofaxEclipse") {
    group = "application"
    description = "Runs an Eclipse instance with all features and plugins included in 'spoofax.eclipse.repository'."
    dependsOn(gradle.includedBuild("spoofax.eclipse").task(":spoofax.eclipse.repository:run"))
  }

  wrapper {
    distributionType = Wrapper.DistributionType.ALL
    setJarFile(".gradlew/wrapper/gradle-wrapper.jar")
  }
}

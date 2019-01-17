fun TaskContainerScope.registerCompositeBuildTask(name: String, description: String) {
  register(name) {
    this.group = "composite build"
    this.description = description
    this.dependsOn(gradle.includedBuilds.map { it.task(":$name") })
  }
}

tasks {
  registerCompositeBuildTask("cleanAll", "Deletes the build directory for all projects in the composite build.")
  registerCompositeBuildTask("checkAll", "Runs all checks for all projects in the composite build.")
  registerCompositeBuildTask("assembleAll", "Assembles the outputs for all projects in the composite build.")
  registerCompositeBuildTask("buildAll", "Assembles and tests all projects in the composite build.")
  registerCompositeBuildTask("publishAll", "Publishes all publications produced by all projects in the composite build.")

  register("runSpoofaxCli") {
    group = "application"
    dependsOn(gradle.includedBuild("spoofax").task(":spoofax.cli:run"))
  }
  register("runSpoofaxEclipse") {
    group = "application"
    dependsOn(gradle.includedBuild("spoofax.eclipse").task(":spoofax.eclipse.repository:run"))
  }

  wrapper {
    distributionType = Wrapper.DistributionType.ALL
    setJarFile(".gradlew/wrapper/gradle-wrapper.jar")
  }
}

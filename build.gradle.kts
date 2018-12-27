tasks {
  register("buildAll") {
    dependsOn(gradle.includedBuilds.map { it.task(":buildAll") })
  }
  register("cleanAll") {
    dependsOn(gradle.includedBuilds.map { it.task(":cleanAll") })
  }

  register("buildCoronium") {
    dependsOn(gradle.includedBuild("coronium").task(":buildAll"))
  }

  register("runSpoofaxCli") {
    dependsOn(gradle.includedBuild("spoofax").task(":spoofax.cli:run"))
  }
  register("runSpoofaxEclipse") {
    dependsOn(gradle.includedBuild("spoofax.eclipse").task(":spoofax.eclipse.repository:run"))
  }

  register("spoofaxEclipseRepositoryDependencies") {
    dependsOn(gradle.includedBuild("spoofax.eclipse").task(":spoofax.eclipse.repository:dependencies"))
  }
  register("spoofaxEclipseFeatureDependencies") {
    dependsOn(gradle.includedBuild("spoofax.eclipse").task(":spoofax.eclipse.feature:dependencies"))
  }
  register("spoofaxEclipseMetaFeatureDependencies") {
    dependsOn(gradle.includedBuild("spoofax.eclipse").task(":spoofax.eclipse.meta.feature:dependencies"))
  }
  register("spoofaxEclipsePluginDependencies") {
    dependsOn(gradle.includedBuild("spoofax.eclipse").task(":spoofax.eclipse.plugin:dependencies"))
  }
  register("spoofaxEclipseMetaPluginDependencies") {
    dependsOn(gradle.includedBuild("spoofax.eclipse").task(":spoofax.eclipse.meta.plugin:dependencies"))
  }
}

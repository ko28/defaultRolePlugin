# Default Role Plugin
Extends Spinnaker's [fiat][] service to add a default role called 'public'.

## Usage

### Precompiled distribution
Precompiled distributions can be downloaded here: https://github.com/ko28/defaultRolePlugin/tags

If using the [Spinnaker Operator][], you can use this patch. 
```yaml
#-----------------------------------------------------------------------------------------------------------------
# Example configuration for enabling a plugin into spinnaker
#-----------------------------------------------------------------------------------------------------------------
apiVersion: spinnaker.io/v1alpha2
kind: SpinnakerService
metadata:
  name: spinnaker
spec:
  spinnakerConfig:
    # spec.spinnakerConfig.profiles - This section contains the YAML of each service's profile
    profiles:
      fiat:
        spinnaker:
          extensibility:
            plugins:
              ko28.DefaultRolePlugin:
                enabled: true
                version: "1.0.0"
                config: {}
            repositories:
              ko28Repo:
                id: ko28Repo
                url: https://raw.githubusercontent.com/ko28/defaultRolePlugin/master/repositories.json
```
### Local deployment
1) Run `./gradlew releaseBundle`
2) Put the `/build/distributions/<project>-<version>.zip` into the [configured plugins location for your service](https://pf4j.org/doc/packaging.html).
3) Configure the Spinnaker service. Put the following in the service yml to enable the plugin and configure the extension.
```yaml
spinnaker:
  extensibility:
    plugins:
      ko28.DefaultRolePlugin:
        enabled: true
```

To debug the plugin inside a Spinnaker service (like Orca) using IntelliJ Idea follow these steps:

1) Run `./gradlew releaseBundle` in the plugin project.
2) Copy the generated `.plugin-ref` file under `build` in the plugin project submodule for the service to the `plugins` directory under root in the Spinnaker service that will use the plugin .
3) Link the plugin project to the service project in IntelliJ (from the service project use the `+` button in the Gradle tab and select the plugin build.gradle).
4) Configure the Spinnaker service the same way specified above.
5) Create a new IntelliJ run configuration for the service that has the VM option `-Dpf4j.mode=development` and does a `Build Project` before launch.
6) Debug away...

[fiat]: https://github.com/spinnaker/fiat/
[Spinnaker Operator]: https://github.com/armory/spinnaker-operator
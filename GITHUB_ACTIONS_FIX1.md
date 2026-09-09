# GitHub Actions FIX1

The first Windows run compiled `:core:compileJava` and `:desktop:compileJava` successfully,
but failed at `:desktop:dist` because the custom fat-JAR task tried to expand
`core/build/libs/core-1.0.0.jar` before `:core:jar` had produced it.

Fix:
- `desktop:dist` now explicitly `dependsOn ':core:jar'`.
- Windows workflow explicitly runs `:core:jar :desktop:dist`.
- `BUILD_WINDOWS_EXE.bat` uses the same safe task order.
- Source verifier contains a regression check for this dependency.

The 86 `unchecked` warnings in the uploaded log are legacy-source warnings and were not the build failure.

buildscript {
  val sqlDelightVersion = "1.5.5"

  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")
    classpath("com.android.tools.build:gradle:8.0.2")
    classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}

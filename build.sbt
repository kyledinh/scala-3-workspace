val scala3Version = "3.2.2"

externalResolvers += "ScalaLibrary packages" at "https://maven.pkg.github.com/kyledinh/scala-library"

lazy val root = project
  .in(file("."))
  .settings(
    name                                   := "scala-3-workspace",
    version                                := "0.1.0-SNAPSHOT",
    scalaVersion                           := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "com.kyledinh" %% "scala-library_3" % "0.1.0-SNAPSHOT"
  )

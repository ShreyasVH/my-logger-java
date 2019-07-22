name := "my-logger-java"

version := "1.0.0"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.projectlombok" % "lombok" % "1.18.8"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
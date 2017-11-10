import sbt.Keys._
import sbt._
import sbtrelease.Version

name := "inputform-backend"

resolvers += Resolver.sonatypeRepo("public")
scalaVersion := "2.12.2"
releaseNextVersion := { ver => Version(ver).map(_.bumpMinor.string).getOrElse("Error") }
assemblyJarName in assembly := "inputform-backend.jar"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-lambda-java-events" % "1.3.0",
  "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.1",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.12" % "2.9.1",
  "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test",
  "org.mockito" % "mockito-core" % "2.7.22"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings")

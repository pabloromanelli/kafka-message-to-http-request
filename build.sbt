name := "kafka-message-to-http-request"

version := "0.4"

scalaVersion := "2.12.6"

scalacOptions := Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.google.inject" % "guice" % "4.2.0",
  "org.apache.kafka" % "kafka-clients" % "1.1.0",
  "org.apache.kafka" % "kafka-streams" % "1.1.0",
  "com.typesafe" % "config" % "1.3.3",
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.1.9",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.6",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.6",
  "io.lettuce" % "lettuce-core" % "5.0.5.RELEASE"
)

enablePlugins(JavaAppPackaging)

dockerRepository := Some("graphpathai")

dockerUpdateLatest := true

import com.typesafe.sbt.packager.docker._

dockerCommands := Seq(
  Cmd("FROM", "openjdk:8u171-jre-alpine3.8"),
  Cmd("WORKDIR", "/opt/docker"),
  Cmd("USER", "daemon"),
  ExecCmd("ENTRYPOINT", "java", "-Xms32m", "-Xmx128m", "-cp", "lib/*", "ai.graphpath.Main"),
  Cmd("ADD", "--chown=daemon:daemon opt /opt")
)

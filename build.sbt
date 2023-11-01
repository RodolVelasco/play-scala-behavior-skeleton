name := """play-scala-behavior-skeleton"""
organization := "com.applaudo.services"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  guice,
  "com.typesafe.akka" %% "akka-actor" % "2.6.18",
  "org.beanio" % "beanio" % "2.1.0",
  "commons-net" % "commons-net" % "3.8.0",
  "ch.qos.logback" % "logback-classic" % "1.2.6",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)

lazy val apiDeps = Seq(
  //"io.swagger" %% "swagger-scala-module" % "1.0.6",
  //"io.swagger" %% "swagger-play2" % "1.7.1",
  //"org.webjars" % "swagger-ui" % "3.25.1",
  //"com.jcraft" % "jsch" % "0.1.55"
)


libraryDependencies ++= apiDeps
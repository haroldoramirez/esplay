name := """esplay"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.34"
  //"org.webjars" % "jquery" % "2.1.3",
  //"org.webjars" % "bootstrap" % "3.3.2-1"
)

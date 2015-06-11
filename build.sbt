name := """esplay"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.34",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "javax.mail" % "mail" % "1.4.7",
  "com.typesafe.play" %% "play-mailer" % "2.4.1",
  "com.feth" % "play-easymail" % "0.6.7"

)

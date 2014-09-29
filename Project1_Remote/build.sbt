name := "Project1_Remote"

version := "1.0"

scalaVersion := "2.10.2"

mainClass := Some("BitCoinRemote")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.6",
  "com.typesafe.akka" %% "akka-remote" % "2.3.6"
)

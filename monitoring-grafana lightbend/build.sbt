
version := "1.0"
scalaVersion := "2.13.8"
mainClass := Some("Server")

val AkkaVersion = "2.6.20"
val AkkaHttpVersion = "10.5.2"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime
assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case PathList("application.conf") => MergeStrategy.concat
  case PathList("reference.conf") => MergeStrategy.concat
  case PathList("version.conf") => MergeStrategy.concat
  case _ => MergeStrategy.first
}

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
  "com.github.pureconfig" %% "pureconfig" % "0.17.2",
  "com.typesafe.akka" %% "akka-stream-kafka" % "4.0.0",
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.8.1"
)

// Enable the Lightbend Telemetry (Cinnamon) sbt plugin
lazy val app = project in file(".") enablePlugins (Cinnamon)

resolvers ++= Seq(
      "lightbend-commercial-mvn" at s"https://repo.lightbend.com/pass/RIjF5KEyjK7YOz_5jURVez4F-Qsfto7VWXTybFMzIYoO2_CV/commercial-releases"
    )

enablePlugins(JavaAppPackaging, DockerPlugin)
dockerBaseImage := "docker.io/library/eclipse-temurin:17.0.3_7-jre-jammy"
dockerUsername := sys.props.get("docker.username")
dockerRepository := sys.props.get("docker.registry")

// Add the Cinnamon Agent for run and test
run / cinnamon := true
test / cinnamon := true

// Set the Cinnamon Agent log level
cinnamonLogLevel := "INFO"


// Use Coda Hale Metrics
libraryDependencies += Cinnamon.library.cinnamonCHMetrics
// Use Akka instrumentation
libraryDependencies += Cinnamon.library.cinnamonAkkaTyped
libraryDependencies += Cinnamon.library.cinnamonAkkaPersistence
libraryDependencies += Cinnamon.library.cinnamonAkkaStream
libraryDependencies += Cinnamon.library.cinnamonAkkaProjection
// Use Akka HTTP instrumentation
libraryDependencies += Cinnamon.library.cinnamonAkkaHttp
// Use Akka gRPC instrumentation
libraryDependencies += Cinnamon.library.cinnamonAkkaGrpc

libraryDependencies += Cinnamon.library.cinnamonPrometheus
libraryDependencies += Cinnamon.library.cinnamonPrometheusHttpServer
libraryDependencies += Cinnamon.library.cinnamonAkkaTyped
libraryDependencies += Cinnamon.library.cinnamonAgent


libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.32"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.6"

libraryDependencies ++= Seq(
  Cinnamon.library.cinnamonCHMetrics,
  Cinnamon.library.cinnamonAkkaTyped,
  Cinnamon.library.cinnamonAkkaPersistence,
  Cinnamon.library.cinnamonAkkaStream,
  Cinnamon.library.cinnamonAkkaProjection,
  Cinnamon.library.cinnamonAkkaHttp,
  Cinnamon.library.cinnamonAkkaGrpc
)


assembly / assemblyJarName := "LogisticDeliverSimulator.jar"
name := "orders"

organization := "reactivebbq"

version := "0.1-SNAPSHOT"

scalaVersion := "2.13.4"

val akkaHttpVersion = "10.2.2"
val akkaVersion    = "2.6.10"
val akkaManagementVersion =  "1.0.9"
val akkaEnhancementsVersion = "1.1.16"
val slf4jVersion = "1.7.30"
val logbackVersion = "1.2.3"
val scalaTestVersion = "3.2.2"
val typesafeConfigVersion = "1.4.1"
val h2Version = "1.4.200"
val hibernateVersion = "5.4.27.Final"

fork := true
parallelExecution in ThisBuild := false

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-cluster"         % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-sharding"% akkaVersion,
  "com.typesafe.akka" %% "akka-serialization-jackson" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.lightbend.akka.management" %% "akka-management" % akkaManagementVersion,
  "com.lightbend.akka.management" %% "akka-management-cluster-http" % akkaManagementVersion,
  "com.lightbend.akka" %% "akka-diagnostics" % akkaEnhancementsVersion,

  "com.h2database" % "h2" % h2Version,
  "org.hibernate" % "hibernate-entitymanager" % hibernateVersion,
  "org.hibernate" % "hibernate-c3p0" % hibernateVersion,

  //Logback
  "ch.qos.logback" % "logback-classic" % logbackVersion,

  //Test dependencies
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % scalaTestVersion% Test,

  // Lightbend Telemetry dependencies
  Cinnamon.library.cinnamonAkka,
  Cinnamon.library.cinnamonAkkaHttp,
  Cinnamon.library.cinnamonJvmMetricsProducer,
  Cinnamon.library.cinnamonPrometheus,
  Cinnamon.library.cinnamonPrometheusHttpServer,
)

dependencyOverrides ++= Seq(
  "org.slf4j" % "slf4j-api" % slf4jVersion,
  "com.typesafe" % "config" % typesafeConfigVersion,
  "com.typesafe.akka" %% "akka-actor"% akkaVersion,
  "com.typesafe.akka" %% "akka-cluster"         % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-sharding"% akkaVersion,
  "com.typesafe.akka" %% "akka-coordination"% akkaVersion,
  "com.typesafe.akka" %% "akka-stream"% akkaVersion,
  "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-core"            % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
)

enablePlugins(Cinnamon)

cinnamon in run := true
cinnamon in test := false

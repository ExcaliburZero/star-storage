name := "star-storage"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= {
  Seq(
    "com.sparkjava" % "spark-core" % "2.5.4",
    "io.argonaut" %% "argonaut" % "6.2-RC2"
  )
}
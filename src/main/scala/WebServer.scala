package starstorage

import java.util.Scanner
import spark.Spark._

import argonaut._, Argonaut._

object WebServer extends App {
  def main() {
    val kb: Scanner = new Scanner(System.in)

    post("/add", (req, res) => {
        val star: Option[Star] = req.body().decodeOption[Star]
        star match {
          case Some(s) => {
            println(s)
            s
          }
          case _ => "Invalid star."
        }
      }
    )

    println("Started web server ...")
    kb.nextLine()

    stop()
  }

  main()
}
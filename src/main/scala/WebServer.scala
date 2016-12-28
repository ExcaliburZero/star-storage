package starstorage

import java.util.Scanner

import spark.Spark._
import argonaut._
import Argonaut._
import java.sql.DriverManager
import java.sql.Connection

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException

case class Database(driver: String, url: String, username: String, password: String)

object WebServer extends App {
  def connectToDatabase(db: Database): Connection = {
    Class.forName(db.driver)
    DriverManager.getConnection(db.url, db.username, db.password)
  }

  def main() {
    val kb: Scanner = new Scanner(System.in)

    print("Password: ")

    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost/starstorage"
    val username = "starstorage"
    val password = kb.nextLine()
    val database: Database = Database(driver, url, username, password)

    post("/add", (req, res) => {
      val star: Option[Star] = req.body().decodeOption[Star]
      star match {
        case Some(s) => {
          val conn = connectToDatabase(database)
          var success = true
          try {
            val insert = "INSERT INTO Star VALUES (?, ?, ?)"
            val statement = conn.prepareStatement(insert)
            statement.setString(1, s.id)
            statement.setString(2, s.category)
            statement.setDouble(3, s.period)
            statement.executeUpdate()
          } catch {
            case e => {
              success = false
              println(e.getClass)
              //e.printStackTrace
            }
          }
          conn.close()
          if (success) {
            println(s)
            s
          } else {
            "Invalid star."
          }
        }
        case _ => "Incorrectly formatted star."
      }
    }
    )

    println("Started web server ...")
    kb.nextLine()

    stop()
  }

  main()
}
package starstorage

import argonaut._, Argonaut._

case class Star(id: String, category: String, period: Double)

object Star {
  implicit def PersonCodecJson =
    casecodec3(Star.apply, Star.unapply)("id", "category", "period")
}
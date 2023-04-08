package com.kyledinh.problems

import scala.annotation.tailrec

case class Res(start: Int, end: Int) {
  def overlap(other: Res): Boolean =
    //      5 --------15        this
    // 1 ------ 10              other
    //              12----20    other
    if this.start < other.end then true
    else if this.end > other.start && this.end < other.end then true
    else false
}

def countRequiredReservations(res: Vector[Res]): Int = {

  def overlapsWithAnyInVector(res: Res, others: Vector[Res]): Boolean = {
    @tailrec
    def processThruOthers(res: Res, remaining: Vector[Res]): Boolean =
      if (remaining.isEmpty) false
      else if (res.overlap(remaining.head)) true
      else processThruOthers(res, remaining.tail)
    processThruOthers(res, others)
  }

  @tailrec
  def processReservations(remaining: Vector[Res], accumulated: Vector[Res], result: Int): Int =
    if (remaining.isEmpty) result
    else if (overlapsWithAnyInVector(remaining.head, accumulated)) {
      processReservations(remaining.tail, accumulated :+ remaining.head, result + 1)
    } else processReservations(remaining.tail, accumulated :+ remaining.head, result)

  processReservations(res, Vector[Res](), 1)
}

// This is the first stab at the problem, it does a double loop though, hmmmmm
// I need to clean up the names, find a better algorithm
object ResApp extends App {
  val reservations = Vector(Res(1, 10), Res(11, 15), Res(20, 40), Res(30, 50))
  val count        = countRequiredReservations(reservations)
  println(s"Room count: $count")
}

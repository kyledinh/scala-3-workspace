package com.kyledinh.problems

import scala.annotation.tailrec
import scala.collection.mutable.Stack

case class Res(start: Int, end: Int) {
  def overlaps(other: Res): Boolean =
    //      5 --------15        this
    // 1 ------ 10              other
    //              12----20    other
    if this.start < other.end && this.end > other.start then true
    else if this.end > other.start && this.end < other.end then true
    else false
}

def countRequiredRooms(res: Vector[Res]): Int = {

  def overlapsWithAnyInVector(res: Res, others: Vector[Res]): Boolean = {
    @tailrec
    def processThruOthers(res: Res, remaining: Vector[Res]): Boolean =
      if (remaining.isEmpty) false
      else if (res.overlaps(remaining.head)) true
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
// best case N (you need N rooms), worst case N * N (you need 1 room)
// I need to clean up the names, find a better algorithm
object ResApp extends App {
  val reservations = Vector(Res(1, 10), Res(11, 15), Res(20, 40), Res(30, 50))
  val count        = countRequiredRooms(reservations)
  println(s"Room count: $count")
}

// BETTER ALGORITHMN - LINEAR 
def buildMapOfRes(reservations: Vector[Res]): Map[Int, Int] = {

  /*
  // OLD
  def updateMap(key: Int, map: Map[Int, Int]): Map[Int, Int] =
    if (map.contains(key)) {
      val curCount = map(key)
      map.updated(key, curCount + 1)
    } else map.updated(key, 1)

  def loopRes(res: Res, map: Map[Int, Int]): Map[Int, Int] = {
    var maps = Stack[Map[Int, Int]]()
    maps.push(map)
    for (i <- res.start to res.end) maps.push(updateMap(i, maps.pop()))
    return maps.pop()
  }

  @tailrec
  def processRes(remaining: Vector[Res], map: Map[Int, Int]): Map[Int, Int] =
    if (remaining.isEmpty) map
    else processRes(remaining.tail, loopRes(remaining.head, map))

  var timeMap = Map[Int, Int]()
  processRes(reservations, timeMap)
   */

  def mergeMap(a: Map[Int, Int], b: Map[Int, Int])(implicit num: Numeric[Int]): Map[Int, Int] =
    b ++ a.map { case (key, value) => key -> num.plus(value, b.getOrElse(key, num.zero)) }

  def resToMap(res: Res): Map[Int, Int] =
    (res.start to res.end).map(n => (n, 1)).toMap

  val resMaps = reservations.map(resToMap)
  resMaps.reduce((a, b) => mergeMap(a, b))
}

object BetterResApp extends App {
  // val reservations  = Vector(Res(1, 13), Res(11, 15), Res(20, 40), Res(30, 44))
  val reservations = Vector(Res(1, 3), Res(2, 3), Res(3, 6), Res(1, 7))
  var m            = buildMapOfRes(reservations)
  println(s"Timeblock size: ${m.size}")
  println(m.toString())

  if m.size == 0 then {
    println(s"Time slots are zero, no rooms are needed.")
  } else {
    val (time, cnt) = m.maxBy(_._2)
    println(s"Room count: $cnt")
  }
}

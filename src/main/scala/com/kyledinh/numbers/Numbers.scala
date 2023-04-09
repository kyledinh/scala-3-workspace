package com.kyledinh.numbers

import scala.annotation.tailrec
import com.kyledinh.lists.{UberList, UberListNil}
import scala.math.*
import scala.collection.mutable.Stack
object Prime {

  def sqrtFloor(n: Int): Int = {
    val root = sqrt(n.toDouble)
    return ceil(root).toInt
  }

  def removeFromSieve(n: Int, top: Int, sieve: Vector[Int]): Vector[Int] = {
    val seq = (2 * n) to top by n
    sieve.diff(seq)
  }

  def loopRemoveFromSieve(topFactor: Int, max: Int, sieve: Vector[Int]): Vector[Int] = {
    var sieves = Stack[Vector[Int]]()
    sieves.push(sieve)
    for (i <- 2 to topFactor) sieves.push(removeFromSieve(i, max, sieves.pop()))
    return sieves.pop()
  }

  def makeSieve(max: Int): Vector[Int] = {
    var sieve     = Vector.from(3 to max)
    var topFactor = sqrtFloor(max)
    return loopRemoveFromSieve(topFactor, max, sieve)
  }

  def is(n: Int): Boolean = {

    def hasZeroRemainder(dividend: Int, divisor: Int): Boolean =
      if (divisor == 0) throw new ArithmeticException
      else if ((dividend % divisor) > 0) false
      else true

    @tailrec
    def byStepIsPrimeTailrec(candidates: Vector[Int], n: Int): Boolean =
      if (candidates.isEmpty) true
      else if (hasZeroRemainder(n, candidates.head)) false
      else byStepIsPrimeTailrec(candidates.tail, n)

    val sieve = makeSieve(n - 1)

    if (n == 0) true
    else if (n == 1) true
    else if (n == 2) true
    else if (n < 0) false
    else byStepIsPrimeTailrec(sieve, n)
  }

}

object Fibonacci {

  def fibNextVal(prev: Int, cur: Int): Int = prev + cur

  @tailrec
  def fibonateTailrec(maxStep: Int, curStep: Int, result: Vector[Int]): Vector[Int] = {
    val nextVal = fibNextVal(result(curStep - 2), result(curStep - 1)) // steps counted from 1, index from 0
    if (maxStep == curStep) result
    else fibonateTailrec(maxStep, curStep + 1, result :+ nextVal)
  }

  def toSteps(steps: Int): Vector[Int] =
    if (steps == 0) Vector()
    else if (steps == 1) Vector(0)
    else if (steps == 2) Vector(0, 1)
    else fibonateTailrec(steps, 3, Vector[Int](0, 1, 1))

  def startAtForNSteps(prev: Int, start: Int, steps: Int): Vector[Int] =
    // starts at step 3, early steps are known
    val next = prev + start
    if (steps < 3) Vector[Int](prev, start)
    else if (steps < 4) Vector[Int](prev, start, next)
    else fibonateTailrec(steps, 3, Vector[Int](prev, start, next))

}

package com.kyledinh.numbers

import scala.annotation.tailrec
import com.kyledinh.lists.{UberList, UberListNil}

object Prime {

  def is(n: Int): Boolean = {

    def hasZeroRemainder(dividend: Int, divisor: Int): Boolean =
      if (divisor == 0) throw new ArithmeticException
      else if ((dividend % divisor) > 0) false
      else true

    @tailrec
    def byStepIsPrimeTailrec(candidates: UberList[Int], n: Int): Boolean =
      if (candidates.isEmpty) true
      else if (hasZeroRemainder(n, candidates.head)) false
      else byStepIsPrimeTailrec(candidates.tail, n)

    if (n == 0) true
    else if (n == 1) true
    else if (n == 2) true
    else if (n < 0) throw new ArithmeticException
    else byStepIsPrimeTailrec(UberList.from(2 to (n - 1)), n)
    // Improved the generation of candidate divisors
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

  def toSteps(n: Int): Vector[Int] =
    if (n == 0) Vector()
    else if (n == 1) Vector(0)
    else if (n == 2) Vector(0, 1)
    else fibonateTailrec(n, 3, Vector[Int](0, 1, 1))
}

object NumberProblems extends App {

  println(s"Is 7 prime: ${Prime.is(7)}")
  println(s"Is 12 prime: ${Prime.is(12)}")
  println(s"Is 5 prime: ${Prime.is(5)}")
  println(s"Is 39 prime: ${Prime.is(39)}")
  println(s"Is 701 prime: ${Prime.is(701)}")
  println("Number Problems")
}

package com.kyledinh.numbers

object NumberProblems extends App {

  println(s"Is 7 prime: ${Prime.is(7)}")
  println(s"Is 12 prime: ${Prime.is(12)}")
  println(s"Is 5 prime: ${Prime.is(5)}")
  println(s"Is 39 prime: ${Prime.is(39)}")
  println(s"Is 701 prime: ${Prime.is(701)}")
  println("Number Problems")

  val fib = Fibonacci.startAtForNSteps(5, 6, 5)
  println(s"fib: $fib")
}

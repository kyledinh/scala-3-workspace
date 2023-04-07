package com.kyledinh.lists

object ListApp extends App {

  val ul3 = 5 :: 6 :: 7 :: UberListNil // Cons(5, Cons(6), Cons(7), UberListNil)))
  println(ul3.atIndex(1))
  println(ul3.atIndex(2))
  println(s"Length: ${ul3.length}")
  println(s"Reverse: ${ul3.reverse}")

  println(UberList.from(46 to 100)) // [46 ... 100]

  val ul4 = 33 :: 34 :: 35 :: UberListNil
  println(s"ul3 : $ul3")
  println(s"ul4 : $ul4")
  println(s"ul3 ++ ul4:  ${ul3 ++ ul4}")

}

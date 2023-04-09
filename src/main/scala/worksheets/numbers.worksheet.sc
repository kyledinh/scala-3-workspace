import com.kyledinh.numbers.Fibonacci
import com.kyledinh.numbers.Prime
import com.kyledinh.lists.*

val xs       = 1 to 10 by 3
val isPrime7 = Prime.is(7)

if (isPrime7) println("Yup! 7 is prime")

val x = Fibonacci.toSteps(13)
val y = Fibonacci.startAtForNSteps(10, 5, 7)

val v = Vector.from(3 to 10)

val s1 = Prime.makeSieve(100)

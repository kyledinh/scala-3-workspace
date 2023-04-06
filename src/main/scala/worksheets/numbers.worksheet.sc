import com.kyledinh.numbers.Fibonacci
import com.kyledinh.numbers.Prime
import com.kyledinh.lists.*

val xs       = 1 to 10 by 3
val isPrime7 = Prime.is(7)

if (isPrime7) println("Yup! 7 is prime")

val x = Fibonacci.toSteps(8)

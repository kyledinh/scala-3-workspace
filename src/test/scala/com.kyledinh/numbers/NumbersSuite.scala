import com.kyledinh.lists.UberListNil
import com.kyledinh.numbers.Prime

class NumbersSuite extends munit.FunSuite {
  test("Prime methods") {
    assertEquals(Prime.is(7), true)
    assertEquals(Prime.is(8), false)
    assertEquals(Prime.is(11), true)
    assertEquals(Prime.is(18), false)
  }
}

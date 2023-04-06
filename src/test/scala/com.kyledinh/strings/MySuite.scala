import com.kyledinh.strings.StrUtil

class StringSuite extends munit.FunSuite {
  test("Testing StrUtil ") {
    val m1       = StrUtil.charCountMap("apple")
    val expected = Map('a' -> 1, 'p' -> 2, 'l' -> 1, 'e' -> 1)
    assertEquals(m1, expected)
  }
}

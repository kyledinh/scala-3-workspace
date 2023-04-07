import com.kyledinh.strings.StrUtil
import com.kyledinh.strings.*

class StringSuite extends munit.FunSuite {
  test("Testing StrUtil ") {
    val m1       = StrUtil.charCountMap("apple")
    val expected = Map('a' -> 1, 'p' -> 2, 'l' -> 1, 'e' -> 1)
    assertEquals(m1, expected)
  }

  test("Testing Anagram ") {
    assertEquals(checkAnagram("cat", "tac"), true)
    assertEquals(checkAnagram("listen", "silent"), true)
    assertEquals(checkAnagram("level", "llama"), false)
  }

  test("Testing Parentheses ") {
    assertEquals(validParentheses("ac"), true)
    assertEquals(validParentheses(")"), false)
    assertEquals(validParentheses("("), false)
    assertEquals(validParentheses("()"), true)
    assertEquals(validParentheses("()()"), true)
    assertEquals(validParentheses("(()())"), true)
  }
}

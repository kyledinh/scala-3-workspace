import com.kyledinh.lists.UberListNil
class ListsSuite extends munit.FunSuite {
  test("UberList methods") {
    val ul0 = UberListNil 
    assertEquals(ul0.isEmpty, true)
  }
}

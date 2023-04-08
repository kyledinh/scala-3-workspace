import com.kyledinh.strings.StrUtil
import com.kyledinh.strings.checkAnagram
import com.kyledinh.strings.validParentheses

val str1  = "This is a strange world"
val mpstr = StrUtil.charCountMap(str1)
val mp    = StrUtil.charCountMap("apple")

var acheck = checkAnagram("ttac", "cat")

val vp1 = validParentheses("())......")

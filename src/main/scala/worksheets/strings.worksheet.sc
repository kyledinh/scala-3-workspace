import com.kyledinh.strings.StrUtil
import com.kyledinh.strings.checkAnagram

val str1  = "This is a strange world"
val mpstr = StrUtil.charCountMap(str1)
val mp    = StrUtil.charCountMap("apple")

var acheck = checkAnagram("tac", "cat")

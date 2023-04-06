package com.kyledinh.strings

import scala.annotation.tailrec

object StrUtil {

  def charCountMap(str: String): Map[Char, Int] = {

    def updateCharMap(ch: Char, map: Map[Char, Int]): Map[Char, Int] =
      if (map.contains(ch)) {
        val curCount = map(ch)
        map.updated(ch, curCount + 1)
      } else map.updated(ch, 1)

    @tailrec
    def charCountTailrec(remaining: Array[Char], result: Map[Char, Int]): Map[Char, Int] =
      if (remaining.isEmpty) result
      else charCountTailrec(remaining.tail, updateCharMap(remaining.head, result))
    charCountTailrec(str.toCharArray(), Map[Char, Int]())
  }

}

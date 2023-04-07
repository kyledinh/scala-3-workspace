package com.kyledinh.strings

import scala.annotation.tailrec
import scala.collection.mutable.Stack

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

def checkAnagram(a: String, b: String): Boolean =
  StrUtil.charCountMap(a) == StrUtil.charCountMap(b)

def validParentheses(str: String): Boolean = {

  @tailrec
  def validateParens(remaining: Array[Char], stack: Stack[Char]): Boolean =
    if (remaining.isEmpty) (stack.size == 0)
    else if (remaining.head == '(') {
      stack.push(remaining.head)
      validateParens(remaining.tail, stack)
    } else if (remaining.head == ')') {
      if (stack.size == 0) return false
      stack.pop()
      validateParens(remaining.tail, stack)
    } else validateParens(remaining.tail, stack)

  validateParens(str.toCharArray(), Stack[Char]())
}

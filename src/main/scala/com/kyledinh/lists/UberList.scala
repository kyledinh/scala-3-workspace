package com.kyledinh.lists

import scala.annotation.tailrec

sealed abstract class UberList[+T] {
  def head: T
  def tail: UberList[T]
  def isEmpty: Boolean
  // prepend(), Right Associative method
  def ::[S >: T](elem: S): UberList[S] = new ::(elem, this)

  def atIndex(index: Int): T
  def length: Int
  def reverse: UberList[T]
  def ++[S >: T](importedList: UberList[S]): UberList[S]
}

case object UberListNil extends UberList[Nothing] {
  override def head: Nothing                                            = throw new NoSuchElementException
  override def tail: UberList[Nothing]                                  = throw new NoSuchElementException
  override def isEmpty: Boolean                                         = true
  override def toString: String                                         = "[]"
  override def atIndex(index: Int): Nothing                             = throw new NoSuchElementException
  override def length: Int                                              = 0
  override def reverse: UberList[Nothing]                               = UberListNil
  override def ++[S >: Nothing](importedList: UberList[S]): UberList[S] = importedList
}

// Right Associative append method
case class ::[+T](override val head: T, override val tail: UberList[T]) extends UberList[T] {
  override def isEmpty: Boolean = false
  override def toString: String = {

    @tailrec
    def toStringTailrec(remaining: UberList[T], result: String): String =
      if (remaining.isEmpty) result
      else if (remaining.tail.isEmpty) s"$result${remaining.head}"
      else toStringTailrec(remaining.tail, s"$result${remaining.head}, ")

    "[" + toStringTailrec(this, "") + "]"
  }

  override def atIndex(index: Int): T = {
    @tailrec
    def atIndexTailrec(remaining: UberList[T], curIndex: Int): T =
      if (curIndex == index) remaining.head
      else atIndexTailrec(remaining.tail, curIndex + 1)
    if (index < 0) throw new NoSuchElementException
    else atIndexTailrec(this, 0)
  }

  override def length: Int = {
    @tailrec
    def lengthTailrec(remaining: UberList[T], curCount: Int): Int =
      if (remaining.isEmpty) curCount
      else lengthTailrec(remaining.tail, curCount + 1)
    lengthTailrec(this, 0)
  }

  override def reverse: UberList[T] = {
    @tailrec
    def reverseTailrec(remaining: UberList[T], result: UberList[T]): UberList[T] =
      if (remaining.isEmpty) result
      else
        reverseTailrec(
          remaining.tail,
          remaining.head :: result
        ) // same as: reverseTailrec(remaining.tail, result.prepend(remaining.head))
    reverseTailrec(this, UberListNil)
  }

  def ++[S >: T](importedList: UberList[S]): UberList[S] = {
    @tailrec
    def prependToImported(remaining: UberList[S], result: UberList[S]): UberList[S] =
      if (remaining.isEmpty) result
      else prependToImported(remaining.tail, remaining.head :: result)
    prependToImported(this.reverse, importedList)
  }
}

object UberList {
  def from[T](iterable: Iterable[T]): UberList[T] = {
    @tailrec
    def convertToUberListTailrec(remaining: Iterable[T], result: UberList[T]): UberList[T] =
      if (remaining.isEmpty) result
      else convertToUberListTailrec(remaining.tail, remaining.head :: result)
    convertToUberListTailrec(iterable, UberListNil).reverse
  }
}

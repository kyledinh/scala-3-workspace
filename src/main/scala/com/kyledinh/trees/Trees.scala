package com.kyledinh.trees

sealed abstract class BTree[+T] {
  def value: T
  def left: BTree[T]
  def right: BTree[T]
  def isEmpty: Boolean
}

case object BEnd extends BTree[Nothing] {

  override def value: Nothing        = throw new NoSuchElementException
  override def left: BTree[Nothing]  = throw new NoSuchElementException
  override def right: BTree[Nothing] = throw new NoSuchElementException
  override def isEmpty               = true

}

// case class BNode[+T](override val value: T, override val left: BTree[T], override val right: BTree[T]) extends BTree[T] {

// }

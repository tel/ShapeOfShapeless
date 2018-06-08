package com.jspha.shapeOfShapeless.genericProgramming

import shapeless.{::, HList, HNil}

trait CompareHLists[L <: HList] {
  def apply(x: L, y: L): Int
}

object CompareHLists {

  def ordering[L <: HList](implicit ev: CompareHLists[L]): Ordering[L] =
    new Ordering[L] {
      def compare(x: L, y: L): Int = ev(x, y)
    }

  implicit val CompareHListsHNil: CompareHLists[HNil] =
    new CompareHLists[HNil] {
      def apply(x: HNil, y: HNil): Int = 0
    }

  implicit def CompareHListsHCons[H, T <: HList](
      implicit ord: Ordering[H],
      recur: CompareHLists[T]): CompareHLists[H :: T] =
    new CompareHLists[H :: T] {
      def apply(x: H :: T, y: H :: T): Int = {
        val compareHead = ord.compare(x.head, y.head)
        if (compareHead == 0) { recur(x.tail, y.tail) } else compareHead
      }
    }

}

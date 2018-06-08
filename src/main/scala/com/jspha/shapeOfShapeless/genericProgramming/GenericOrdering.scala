package com.jspha.shapeOfShapeless.genericProgramming

import shapeless.{Generic, HList, Lazy}

trait GenericOrdering[T] {
  def apply(x: T, y: T): Int
}

object GenericOrdering {

  def derive[T](implicit ord: Lazy[GenericOrdering[T]]): Ordering[T] =
    new Ordering[T] {
      def compare(x: T, y: T): Int = ord.value(x, y)
    }

  implicit def GenericOrderingOfCaseClass[T, Repr <: HList](
      implicit g: Generic.Aux[T, Repr],
      ord: Lazy[CompareHLists[Repr]]): GenericOrdering[T] =
    new GenericOrdering[T] {
      def apply(x: T, y: T): Int = ord.value(g.to(x), g.to(y))
    }

}

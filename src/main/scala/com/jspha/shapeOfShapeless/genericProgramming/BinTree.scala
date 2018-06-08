package com.jspha.shapeOfShapeless.genericProgramming

final case class BinTree[A](
    value: A,
    left: Option[BinTree[A]] = None,
    right: Option[BinTree[A]] = None
)

object BinTree {

  implicit def TreeOrdering[A](implicit ev: Ordering[A]): Ordering[BinTree[A]] =
    GenericOrdering.derive[BinTree[A]]

}

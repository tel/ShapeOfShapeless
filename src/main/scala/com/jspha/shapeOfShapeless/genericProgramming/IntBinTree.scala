package com.jspha.shapeOfShapeless.genericProgramming

final case class IntBinTree(
    value: Int,
    left: Option[IntBinTree] = None,
    right: Option[IntBinTree] = None
)

object IntBinTree {

  implicit val TreeOrdering: Ordering[IntBinTree] =
    GenericOrdering.derive[IntBinTree]

}

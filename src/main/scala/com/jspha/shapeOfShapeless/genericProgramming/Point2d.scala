package com.jspha.shapeOfShapeless.genericProgramming

final case class Point2d(x: Double, y: Double)

object Point2d {

  implicit val Point2dOrdering: Ordering[Point2d] = GenericOrdering.derive[Point2d]

}

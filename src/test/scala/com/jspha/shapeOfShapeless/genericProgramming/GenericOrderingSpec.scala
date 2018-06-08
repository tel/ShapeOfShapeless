package com.jspha.shapeOfShapeless.genericProgramming

import org.scalatest.{FunSpec, Matchers}

class GenericOrderingSpec extends FunSpec with Matchers {

  implicit class Compared[T](value: T)(implicit ord: Ordering[T]) {
    def compare(other: T): Int = ord.compare(value, other)
    def equiv(other: T): Boolean = ord.equiv(value, other)
    def <(other: T): Boolean = ord.lt(value, other)
    def >(other: T): Boolean = ord.gt(value, other)
    def <=(other: T): Boolean = ord.lteq(value, other)
    def >=(other: T): Boolean = ord.gteq(value, other)
  }

  describe("Testing Point2d") {
    it("(0, 0) equiv (0, 0)") {
      Point2d(0, 0) equiv Point2d(0, 0) shouldEqual true
    }
    it("(0, 1) < (1, 0)") {
      Point2d(0, 1) < Point2d(1, 0) shouldEqual true
    }
    it("(1, 1) > (0, 1)") {
      Point2d(1, 1) > Point2d(1, 0) shouldEqual true
    }
  }

  describe("Testing IntBinTree") {
    it("singleton trees") {
      IntBinTree(3) equiv IntBinTree(3) shouldBe true
    }
  }

  describe("Testing BinTree[Int]") {
    it("singleton trees") {
      BinTree(3) equiv BinTree(3) shouldBe true
    }
  }

}

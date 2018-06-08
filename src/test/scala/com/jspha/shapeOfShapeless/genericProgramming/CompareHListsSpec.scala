package com.jspha.shapeOfShapeless.genericProgramming

import org.scalatest.{FunSpec, Matchers}
import shapeless.{HList, HNil}

class CompareHListsSpec extends FunSpec with Matchers {

  implicit class HListOrdered[L <: HList](value: L)(
      implicit ord: CompareHLists[L]) {
    val ordering: Ordering[L] = CompareHLists.ordering[L]
    def compare(other: L): Int = ordering.compare(value, other)
    def equiv(other: L): Boolean = ordering.equiv(value, other)
    def <(other: L): Boolean = ordering.lt(value, other)
    def >(other: L): Boolean = ordering.gt(value, other)
    def <=(other: L): Boolean = ordering.lteq(value, other)
    def >=(other: L): Boolean = ordering.gteq(value, other)
  }

  describe("Should work for all HLists with orderable elements") {

    it("Int :: HNil") {
      (3 :: HNil) equiv (3 :: HNil) shouldBe true
      (2 :: HNil) compare (3 :: HNil) shouldBe -1
      (3 :: HNil) compare (2 :: HNil) shouldBe 1
    }

  }

}

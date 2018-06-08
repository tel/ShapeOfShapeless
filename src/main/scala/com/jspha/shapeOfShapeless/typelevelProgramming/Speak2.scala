package com.jspha.shapeOfShapeless.typelevelProgramming

import shapeless.{::, HList, HNil}

trait Speak2[-In <: HList] {
  type Out <: HList
  def apply(in: In): Out
}

object Speak2 {

  implicit val SpeakHNil: Speak2[HNil] = new Speak2[HNil] {
    type Out = HNil
    def apply(in: HNil): HNil = in
  }

  implicit def SpeakInt[Tail <: HList](
      implicit tail: Speak2[Tail]): Speak2[Int :: Tail] =
    new Speak2[Int :: Tail] {
      type Out = String :: tail.Out
      def apply(in: Int :: Tail): Out = in.head.toString :: tail(in.tail)
    }

  implicit def SpeakNonInt[Head, Tail <: HList](
      implicit tail: Speak2[Tail]): Speak2[Head :: Tail] =
    new Speak2[Head :: Tail] {
      type Out = Head :: tail.Out
      def apply(in: Head :: Tail): Out = in.head :: tail(in.tail)
    }

  def speak[In <: HList](in: In)(implicit ev: Speak2[In]): ev.Out = ev(in)

}

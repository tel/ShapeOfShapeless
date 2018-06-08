package com.jspha.shapeOfShapeless.typelevelProgramming

import shapeless.{::, HList, HNil}

trait Speak3[-In <: HList] {
  type Out <: HList
  def apply(in: In): Out
}

trait Speak3_LowPrio {

  implicit def SpeakNonInt[Head, Tail <: HList](
      implicit tail: Speak3[Tail]): Speak3[Head :: Tail] =
    new Speak3[Head :: Tail] {
      type Out = Head :: tail.Out
      def apply(in: Head :: Tail): Out = in.head :: tail(in.tail)
    }

}

object Speak3 extends Speak3_LowPrio {

  implicit val SpeakHNil: Speak3[HNil] = new Speak3[HNil] {
    type Out = HNil
    def apply(in: HNil): HNil = in
  }

  implicit def SpeakInt[Tail <: HList](
      implicit tail: Speak3[Tail]): Speak3[Int :: Tail] =
    new Speak3[Int :: Tail] {
      type Out = String :: tail.Out
      def apply(in: Int :: Tail): Out = in.head.toString :: tail(in.tail)
    }

  def speak[In <: HList](in: In)(implicit ev: Speak3[In]): ev.Out = ev(in)

  type ExList = String :: String :: Int :: Int :: Seq[Int] :: HNil

  val exList: ExList = "foo" :: "bar" :: 3 :: 2 :: Seq(3, 2, 1) :: HNil

  val out: Speak3[ExList]#Out = speak(exList)

  // this still won't work
  //
  // val outTyped: String :: String :: String :: String :: Seq[Int] :: HNil = out

}

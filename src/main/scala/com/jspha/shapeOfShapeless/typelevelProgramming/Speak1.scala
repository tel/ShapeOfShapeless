package com.jspha.shapeOfShapeless.typelevelProgramming

import shapeless.{::, HList, HNil}

trait Speak1[-In <: HList] {
  type Out <: HList
  def apply(in: In): Out
}

object Speak1 {

  val SpeakHNil: Speak1[HNil] = new Speak1[HNil] {
    type Out = HNil
    def apply(in: HNil): HNil = in
  }

  def SpeakInt[Tail <: HList](prev: Speak1[Tail]): Speak1[Int :: Tail] =
    new Speak1[Int :: Tail] {
      type Out = String :: prev.Out
      def apply(in: Int :: Tail): Out = in.head.toString :: prev(in.tail)
    }

  def SpeakNonInt[Head, Tail <: HList](
      prev: Speak1[Tail]): Speak1[Head :: Tail] =
    new Speak1[Head :: Tail] {
      type Out = Head :: prev.Out
      def apply(in: Head :: Tail): Out = in.head :: prev(in.tail)
    }

  type ExList = String :: String :: Int :: Int :: Seq[Int] :: HNil

  val exList: ExList = "foo" :: "bar" :: 3 :: 2 :: Seq(3, 2, 1) :: HNil

  val exListSpeak: Speak1[ExList] =
    SpeakNonInt(SpeakNonInt(SpeakInt(SpeakInt(SpeakNonInt(SpeakHNil)))))

  val out = exListSpeak(exList)

  // This won't compile, Scala can't prove the type to be what we expect
  //
  // val outTyped: String :: String :: String :: String :: Seq[Int] :: HNil = out

}

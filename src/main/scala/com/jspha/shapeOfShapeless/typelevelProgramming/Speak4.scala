package com.jspha.shapeOfShapeless.typelevelProgramming

import shapeless.{::, HList, HNil}

trait Speak4[-In <: HList] {
  type Out <: HList
  def apply(in: In): Out
}

trait Speak4_LowPrio {

  implicit def SpeakNonInt[Head, Tail <: HList](
      implicit tail: Speak4[Tail]): Speak4.Aux[Head :: Tail, Head :: tail.Out] =
    new Speak4[Head :: Tail] {
      type Out = Head :: tail.Out
      def apply(in: Head :: Tail): Out = in.head :: tail(in.tail)
    }

}

object Speak4 extends Speak4_LowPrio {

  type Aux[In <: HList, Out0 <: HList] = Speak4[In] { type Out = Out0 }

  implicit val SpeakHNil: Speak4.Aux[HNil, HNil] = new Speak4[HNil] {
    type Out = HNil
    def apply(in: HNil): HNil = in
  }

  implicit def SpeakInt[Tail <: HList](implicit tail: Speak4[Tail])
    : Speak4.Aux[Int :: Tail, String :: tail.Out] =
    new Speak4[Int :: Tail] {
      type Out = String :: tail.Out
      def apply(in: Int :: Tail): Out = in.head.toString :: tail(in.tail)
    }

  def speak[In <: HList](in: In)(implicit ev: Speak4[In]): ev.Out = ev(in)

  type ExList = String :: String :: Int :: Int :: Seq[Int] :: HNil
  type OutList = String :: String :: String :: String :: Seq[Int] :: HNil

  val exList: ExList = "foo" :: "bar" :: 3 :: 2 :: Seq(3, 2, 1) :: HNil

  // This works now! IntelliJ can even infer it!
  val out: OutList = speak(exList)

  val outInferred: ::[String, ::[String, ::[String, ::[String, ::[Seq[Int], HNil]]]]] = speak(exList)

  val outHead: String = out.head

}

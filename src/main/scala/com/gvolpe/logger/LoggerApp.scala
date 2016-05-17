package com.gvolpe.logger

import scalaz._, Scalaz._
import ComputationService._

object LoggerApp extends App {

  type Result[T] = Writer[List[String], T]

  def subTotal(n: Double): Result[Double] = for {
    x <- addOne(n)            set s"Adding one to $n" :: Nil
    y <- applyMargination(x)  set s"Applying margination to $n" :: Nil
    z <- applyVAT(y)          set s"Applying VAT to $n" :: Nil
  } yield z

  val subTotal1: Result[Double] = subTotal(47.5)
  val subTotal2: Result[Double] = subTotal(77.3)
  val subTotal3: Result[Double] = subTotal(18.0)

  val result: Result[Double] = for {
    t1  <- subTotal1
    t2  <- subTotal2
    t3  <- subTotal3
  } yield total(t1, t2, t3)

  val (report, value) = result.run

  println(s"Computation's Total is: $value")
  println(">>> Report of Transactions <<<")
  report foreach (x => println(s"-> $x"))

}

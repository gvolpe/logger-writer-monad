package com.gvolpe.logger

object ComputationService {

  private val ProfitMargination = 0.07
  private val VAT               = 0.21

  def addOne(n: Double) = n + 1

  def applyMargination(n: Double) = n * ProfitMargination

  def applyVAT(n: Double) = n * VAT

  def total(subtotals: Double*) = subtotals.sum

}

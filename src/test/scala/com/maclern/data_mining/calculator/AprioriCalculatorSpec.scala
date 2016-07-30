package com.maclern.data_mining.calculator

import com.maclern.data_mining._
import org.scalatest._

import scala.collection.mutable

class AprioriCalculatorSpec extends FlatSpec with Matchers {
    
    val transactionList = TransactionList(List(
        List("1", "2", "4"),
        List("3", "4", "5"),
        List("3", "8", "9"),
        List("3", "5", "6", "4"),
        List("7", "1", "2")
    ))
    
    def expected : FrequentItemSet = {
        val table = new mutable.HashMap[List[Item], Int]
        table.put(List(new Item("1")), 2)
        table.put(List(new Item("2")), 2)
        table.put(List(new Item("3")), 3)
        table.put(List(new Item("4")), 2)
        table.put(List(new Item("5")), 2)
        new FrequentItemSet(table)
    }
    
    "The calculator" should "calculate the most frequently used items" in {
        AprioriCalculator(transactionList, 2) should equal (expected)
    }
    
}
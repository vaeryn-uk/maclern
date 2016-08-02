package com.maclern.data_mining.calculator

import com.maclern.data_mining._
import org.scalatest._

import scala.collection.mutable

class AprioriCalculatorSpec extends FlatSpec with Matchers {
    
    val item1 = new Item("1")
    val item2 = new Item("2")
    val item3 = new Item("3")
    val item4 = new Item("4")
    val item5 = new Item("5")
    val item6 = new Item("6")
    val item7 = new Item("7")
    val item8 = new Item("8")
    val item9 = new Item("9")
    
    val equivalentTransactionList = TransactionList(List(
        List("1", "2", "4"),
        List("3", "4", "5"),
        List("3", "8", "9"),
        List("3", "5", "6", "4"),
        List("7", "1", "2")
    ))
    
    val identicalTransactionList = new TransactionList(List(
        new Transaction(List(item1, item2, item4)),
        new Transaction(List(item3, item4, item5)),
        new Transaction(List(item3, item8, item9)),
        new Transaction(List(item3, item5, item6, item4)),
        new Transaction(List(item7, item1, item2))
    ))
    
    def expected : FrequentItemSet = {
        val table = new mutable.HashMap[List[Item], Int]
        table.put(List(new Item("1")), 2)
        table.put(List(new Item("2")), 2)
        table.put(List(new Item("3")), 3)
        table.put(List(new Item("4")), 3)
        table.put(List(new Item("5")), 2)
        new FrequentItemSet(table)
    }
    
    "The calculator" should "calculate the most frequently used items with identical item instances" in {
        AprioriCalculator(identicalTransactionList, 2) should equal (expected)
    }
    
    "The calculator" should "calculate the most frequently used items with equivalent item instances" in {
//        AprioriCalculator(equivalentTransactionList, 2) should equal (expected)
    }
    
}
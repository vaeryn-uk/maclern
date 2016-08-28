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
    
    def itemCount1Expectation : FrequentItemSet = {
        new FrequentItemSet(List(
            (List(new Item("1")), 2),
            (List(new Item("2")), 2),
            (List(new Item("3")), 3),
            (List(new Item("4")), 3),
            (List(new Item("5")), 2)
        ))
    }

    "The calculator" should "calculate frequently with equivalent item set" in {
        AprioriCalculator(equivalentTransactionList, 2) should equal (
            new FrequentItemSet(List(
                (List(new Item("3"), new Item("4"), new Item("5")), 2)
            ))
        )
    }

    "The calculator" should "calculate frequently with identical item set" in {
        AprioriCalculator(identicalTransactionList, 2) should equal (
            new FrequentItemSet(List(
                (List(new Item("3"), new Item("4"), new Item("5")), 2)
            ))
        )
    }

    "The calculator" should "calculate the example from https://www.youtube.com/watch?v=Hk1zFOMLTrw" in {
        AprioriCalculator(
            TransactionList(List(
                List("1", "3", "4"),
                List("2", "3", "5"),
                List("1", "2", "3", "5"),
                List("2", "5"),
                List("1", "3", "5")
            )),
            2
        ) should equal (
            new FrequentItemSet(List(
                (List(new Item("1"), new Item("3"), new Item("5")), 2),
                (List(new Item("2"), new Item("3"), new Item("5")), 2)
            ))
        )
    }

    "The calculator" should "calculate the Wikipedia example, https://en.wikipedia.org/wiki/Apriori_algorithm#Example_2" in {
        AprioriCalculator(
            TransactionList(List(
                List("1", "2", "3", "4"),
                List("1", "2", "4"),
                List("1", "2"),
                List("2", "3", "4"),
                List("2", "3"),
                List("3", "4"),
                List("2", "4")
            )),
            3
        ) should equal (
            new FrequentItemSet(List(
                (List(new Item("1"), new Item("2")), 3),
                (List(new Item("2"), new Item("3")), 3),
                (List(new Item("2"), new Item("4")), 4),
                (List(new Item("3"), new Item("4")), 3)
            ))
        )
    }

    "The calculator" should "calculate the extended Wikipedia example" in {
        AprioriCalculator(
            TransactionList(List(
                List("1", "2", "3", "4", "5"),
                List("1", "2", "3", "4"),
                List("1", "2"),
                List("2", "3", "4", "5"),
                List("2", "3"),
                List("2", "3", "4", "5"),
                List("2", "4")
            )),
            2
        ) should equal (
            new FrequentItemSet(List(
                (List(new Item("1"), new Item("2"), new Item("3"), new Item("4")), 2),
                (List(new Item("2"), new Item("3"), new Item("4"), new Item("5")), 3)
            ))
        )
    }

    "The calculator" should "calculate the extended Wikipedia example... Extended" in {
        AprioriCalculator(
            TransactionList(List(
                List("1", "2", "3", "4", "5", "6"),
                List("1", "2", "3", "4"),
                List("1", "2"),
                List("2", "3", "4", "5"),
                List("2", "3"),
                List("2", "3", "4", "5", "6"),
                List("2", "4")
            )),
            2
        ) should equal (
            new FrequentItemSet(List(
                (List(new Item("2"), new Item("3"), new Item("4"), new Item("5"), new Item("6")), 2)
            ))
        )
    }

    "The calculator" should "calculate the extended Wikipedia example with higher threshold" in {
        AprioriCalculator(
            TransactionList(List(
                List("1", "2", "3", "4", "5", "6"),
                List("1", "2", "3", "4"),
                List("1", "2"),
                List("2", "3", "5"),
                List("2", "3"),
                List("2", "3", "4", "5", "6"),
                List("2", "3", "4", "5", "6"),
                List("2", "3", "4", "5"),
                List("2", "4")
            )),
            4
        ) should equal (
            new FrequentItemSet(List(
                (List(new Item("2"), new Item("3"), new Item("4"), new Item("5")), 4)
            ))
        )
    }
}
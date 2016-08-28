package com.maclern

import com.maclern.data_mining._
import com.maclern.data_mining.calculator.AprioriCalculator

object Main {
    def main(strings: Array[String]) : Unit = {
        val item1 = new Item("1")
        val item2 = new Item("2")
        val item3 = new Item("3")
        val item4 = new Item("4")
        val item5 = new Item("5")

        val transactionList = new TransactionList(List(
            new Transaction(List(item1, item3, item4)),
            new Transaction(List(item2, item3, item5)),
            new Transaction(List(item1, item2, item3, item5)),
            new Transaction(List(item2, item5)),
            new Transaction(List(item1, item3, item5))
        ))

        AprioriCalculator(transactionList, 2)
    }
}

package com.maclern.apriori

import org.scalatest._

class TransactionSpec extends FlatSpec with Matchers {
    
    val foo : Item = new Item("Foo")
    val bar : Item = new Item("Bar")
    val baz : Item = new Item("Baz")
    val transaction : Transaction = new Transaction(List(foo, bar, baz))
    
    "A transaction" should "contain items" in {
        transaction.items should equal (List(foo, bar, baz))
    }
    
    "A transaction" should "print its items" in {
        transaction.toString should be ("Foo, Bar, Baz")
    }
}

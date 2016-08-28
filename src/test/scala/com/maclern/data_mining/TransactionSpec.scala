package com.maclern.data_mining

import com.maclern.data_mining.exception.DuplicateItemException
import com.maclern.data_mining.{Item, Transaction}
import org.scalatest._

class TransactionSpec extends FlatSpec with Matchers {
    
    val foo : Item = new Item("Foo")
    val bar : Item = new Item("Bar")
    val baz : Item = new Item("Baz")
    val transaction : Transaction = new Transaction(List(foo, bar, baz))
    val reverseTransaction = new Transaction(List(baz, bar, foo))
    val duplicate = new Transaction(List(foo, bar, baz))
    val lonelyTransaction = new Transaction(List(foo))
    
    "A transaction" should "contain items" in {
        transaction.items should equal (List(foo, bar, baz))
    }
    
    "A transaction" should "print its items" in {
        transaction.toString should be ("Foo, Bar, Baz")
    }
    
    "A transaction" should "equal another transaction with the same items" in {
        transaction should equal (duplicate)
    }
    
    "A transaction" should "equal another transaction with the same items in a different order" in {
        transaction should equal (reverseTransaction)
    }
    
    "A transaction" should "not equal another transactions with different items" in {
        transaction shouldNot equal (lonelyTransaction)
    }
    
    "A transaction" should "not equal a non-transaction" in {
        transaction shouldNot equal (null)
    }
    
    "A transaction" should "not allow an item more than once" in {
        intercept[DuplicateItemException] {
            new Transaction(List(new Item("item1"), new Item("item1")))
        }
    }

    "A transaction" should "report whether it contains items #1" in {
        transaction.contains(List(new Item("Foo"))) should equal (true)
    }

    "A transaction" should "report whether it contains items #2" in {
        transaction.contains(List(new Item("Foo"), new Item("Bar"))) should equal (true)
    }

    "A transaction" should "report whether it contains items #3" in {
        transaction.contains(List(new Item("Foo"), new Item("Bar"), new Item("Baz"))) should equal (true)
    }

    "A transaction" should "report that it does not contain items" in {
        transaction.contains(List(new Item("Qux"))) should equal (false)
    }
}

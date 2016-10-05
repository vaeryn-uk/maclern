package com.maclern.data_mining

import org.scalatest._

class ItemSpec extends FlatSpec with Matchers {
    "An item" should "have a name" in {
        new Item("Foo").name should be ("Foo")
    }
    
    "An item" should "print its name" in {
        new Item("Foo").toString should be ("Foo")
    }
    
    "An item" should "equal another item with the same name" in {
        new Item("Foo") should equal (new Item("Foo"))
    }
    
    "An item" should "not equal another item with a different name" in {
        new Item("Foo") shouldNot equal (new Item("Bar"))
    }
    
    "An item" should "not equal a non-item" in {
        new Item("Foo") shouldNot equal (null)
    }
}

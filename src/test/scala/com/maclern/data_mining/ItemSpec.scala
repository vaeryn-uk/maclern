package com.maclern.data_mining

import com.maclern.data_mining.Item
import org.scalatest._

class ItemSpec extends FlatSpec with Matchers {
    "An item" should "have a name" in {
        new Item("Foo").name should be ("Foo")
    }
    
    "An item" should "print its name" in {
        new Item("Foo").toString should be ("Foo")
    }
}
package com.maclern.apriori

import org.scalatest._

class ItemSpec extends FlatSpec with Matchers {
    "An item" should "have a name" in {
        new Item("Foo").name should be ("Foo")
    }
}

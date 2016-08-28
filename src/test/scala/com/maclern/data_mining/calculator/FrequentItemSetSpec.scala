package com.maclern.data_mining.calculator

import com.maclern.data_mining.Item
import org.scalatest.{FlatSpec, Matchers}
import scala.collection.mutable

class FrequentItemSetSpec extends FlatSpec with Matchers {
    
    val setA = () => {
        new FrequentItemSet(List(
            (List(new Item("1")), 3),
            (List(new Item("2"), new Item("1")), 1),
            (List(new Item("3")), 2),
            (List(new Item("4")), 3),
            (List(new Item("5"), new Item("4")), 5)
        ))
    }
    
    val setB = () => {
        new FrequentItemSet(List(
            (List(new Item("2"), new Item("1")), 1),
            (List(new Item("1")), 3),
            (List(new Item("4")), 3),
            (List(new Item("5"), new Item("4")), 5),
            (List(new Item("3")), 2)
        ))
    }
    
    val setC = () => {
        new FrequentItemSet(List(
            (List(new Item("2")), 1),
            (List(new Item("1")), 3),
            (List(new Item("4")), 3),
            (List(new Item("5")), 5),
            (List(new Item("3")), 2),
            (List(new Item("6")), 0)
        ))
    }
    
    val setD = () => {
        new FrequentItemSet()
    }
    
    val setE = () => {
        new FrequentItemSet(List(
            (List(new Item("2")), 1),
            (List(new Item("1")), 2),
            (List(new Item("4")), 4),
            (List(new Item("5")), 6),
            (List(new Item("3")), 1)
        ))
    }
    
    "A frequent item set" should "be equal another frequent item set with the same elements, in any order" in {
        setA() should equal (setB())
        setB() should equal (setA())
    }
    
    "A frequent item set" should "not be equal another frequent item set with different items" in {
        setA() shouldNot equal (setC())
        setA() shouldNot equal (setD())
        setB() shouldNot equal (setC())
        setB() shouldNot equal (setD())
        setC() shouldNot equal (setA())
        setC() shouldNot equal (setD())
        setC() shouldNot equal (setE())
        setD() shouldNot equal (setA())
        setD() shouldNot equal (setE())
    }
    
    "A frequent item set" should "not be equal another frequent item set with same items, but different amounts" in {
        setA() shouldNot equal (setE())
        setB() shouldNot equal (setE())
    }
    
    "A frequent item set" should "increment when multi item lists exists" in {
        val itemSet = new FrequentItemSet()
        
        itemSet.add(List(new Item("1"), new Item("2")))
        itemSet.add(List(new Item("1"), new Item("2")))
        
        itemSet.table.find(
            _._1.equals(List(new Item("1"), new Item("2")))
        ).getOrElse((List(), 0))._2 should equal (2)
    }
    
    "A frequent item set" should "increment when multi item lists exists in different orders" in {
        val itemSet = new FrequentItemSet()
        
        itemSet.add(List(new Item("1"), new Item("2")))
        itemSet.add(List(new Item("2"), new Item("1")))
        
        itemSet.table.find(
            _._1.equals(List(new Item("1"), new Item("2")))
        ).getOrElse((List(), 0))._2 should equal (2)
    }

    "A frequent item set" should "be printed as a string" in {
        val itemSet = new FrequentItemSet()

        itemSet.add(List(new Item("1"), new Item("3")))
        itemSet.add(List(new Item("1"), new Item("3")))
        itemSet.add(List(new Item("1"), new Item("3")))
        itemSet.add(List(new Item("1"), new Item("2")))
        itemSet.add(List(new Item("1"), new Item("2")))
        itemSet.add(List(new Item("2"), new Item("3")))
        itemSet.add(List(new Item("3"), new Item("4")))

        itemSet.toString should equal (
            """[1, 2] => 2
              |[1, 3] => 3
              |[2, 3] => 1
              |[3, 4] => 1""".stripMargin
        )
    }

    "A frequent item set" should "be pruned" in {
        val itemSet = new FrequentItemSet()

        itemSet.add(List(new Item("3"), new Item("1")))
        itemSet.add(List(new Item("3"), new Item("1")))
        itemSet.add(List(new Item("3"), new Item("1")))
        itemSet.add(List(new Item("1"), new Item("2")))
        itemSet.add(List(new Item("1"), new Item("2")))
        itemSet.add(List(new Item("2"), new Item("3")))
        itemSet.add(List(new Item("3"), new Item("4")))

        itemSet.prune(2).toString should equal (
            """[1, 2] => 2
              |[1, 3] => 3""".stripMargin
        )
    }

    "A frequent item set" should "be constructed from list, int tuples" in {
        new FrequentItemSet(List(
            (List(new Item("1"), new Item("2")), 2),
            (List(new Item("1"), new Item("3")), 3)
        )).toString should equal(
            """[1, 2] => 2
              |[1, 3] => 3""".stripMargin
        )
    }

    "A frequent item set" should "handle equality checks with multi-item lists" in {
        new FrequentItemSet(List(
            (List(new Item("1"), new Item("2")), 2),
            (List(new Item("2"), new Item("3")), 3)
        )) should equal(
            new FrequentItemSet(List(
                (List(new Item("3"), new Item("2")), 3),
                (List(new Item("1"), new Item("2")), 2)
            ))
        )
    }
}

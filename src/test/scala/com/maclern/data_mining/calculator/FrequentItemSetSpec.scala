package com.maclern.data_mining.calculator

import com.maclern.data_mining.Item
import org.scalatest.{FlatSpec, Matchers}
import scala.collection.mutable

class FrequentItemSetSpec extends FlatSpec with Matchers {
    
    val setA = () => {
        val table = new mutable.HashMap[List[Item], Int]()
        table.put(List(new Item("1")), 3)
        table.put(List(new Item("2")), 1)
        table.put(List(new Item("3")), 2)
        table.put(List(new Item("4")), 3)
        table.put(List(new Item("5")), 5)
        new FrequentItemSet(table)
    }
    
    val setB = () => {
        val table = new mutable.HashMap[List[Item], Int]()
        table.put(List(new Item("2")), 1)
        table.put(List(new Item("1")), 3)
        table.put(List(new Item("4")), 3)
        table.put(List(new Item("5")), 5)
        table.put(List(new Item("3")), 2)
        new FrequentItemSet(table)
    }
    
    val setC = () => {
        val table = new mutable.HashMap[List[Item], Int]()
        table.put(List(new Item("2")), 1)
        table.put(List(new Item("1")), 3)
        table.put(List(new Item("4")), 3)
        table.put(List(new Item("5")), 5)
        table.put(List(new Item("3")), 2)
        table.put(List(new Item("6")), 0)
        new FrequentItemSet(table)
    }
    
    val setD = () => {
        new FrequentItemSet(new mutable.HashMap[List[Item], Int]())
    }
    
    val setE = () => {
        val table = new mutable.HashMap[List[Item], Int]()
        table.put(List(new Item("2")), 1)
        table.put(List(new Item("1")), 2)
        table.put(List(new Item("4")), 4)
        table.put(List(new Item("5")), 6)
        table.put(List(new Item("3")), 1)
        new FrequentItemSet(table)
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
}

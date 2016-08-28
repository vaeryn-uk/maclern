package com.maclern.data_mining.calculator

import com.maclern.data_mining.Item

import scala.collection.immutable.TreeMap
import scala.collection.mutable

private object MyOrdering extends Ordering[List[Item]] {
    def compare(a:List[Item], b:List[Item]) = a.mkString compare b.mkString
}

/**
  * Created by josh on 30/07/16.
  */
class FrequentItemSet(var table: TreeMap[List[Item], Int])
{
    def this() = {
        this(new TreeMap[List[Item], Int]()(MyOrdering))
    }

    def this(items : List[(List[Item], Int)]) = {
        this()
        items.foreach(entry => (1 to entry._2).foreach((int) => add(entry._1)))
    }
    
    def add(list : List[Item]) : Unit = {
        val sortFn = (a : Item, b : Item) => a < b
        val sortedList = list.sortWith(sortFn)

        // Find our existing entry, or initialise a new tuple with amount of zero.
        val newEntry: (List[Item], Int) = table.find(_._1 equals sortedList) getOrElse(sortedList, 0)
        
        // Add the tuple, increasing amount by one.
        table = table.updated(newEntry._1, newEntry._2 + 1)
    }

    def prune(int: Int) : FrequentItemSet = {
        table = table.filter(_._2 >= int)

        this
    }


    override def toString = {
        table.toList
            .map(entry => "[" + entry._1.sortWith((a, b) => a < b).mkString(", ") + "] => " + entry._2.toString).mkString("\n")
    }
}

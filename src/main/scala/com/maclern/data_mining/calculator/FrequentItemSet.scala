package com.maclern.data_mining.calculator

import com.maclern.data_mining.Item

import scala.collection.mutable

/**
  * Created by josh on 30/07/16.
  */
class FrequentItemSet(var table: mutable.HashMap[List[Item], Int])
{
    def this() = {
        this(new mutable.HashMap[List[Item], Int]())
    }

    def this(items : List[(List[Item], Int)]) = {
        this()
        items.foreach(entry => (1 to entry._2).foreach((int) => add(entry._1)))
    }
    
    def add(list : List[Item]) : Unit = {
        val sortFn = (a : Item, b : Item) => a < b

        // Find our existing entry, or initialise a new tuple with amount of zero.
        val newEntry: (List[Item], Int) = table.find(
            _._1.sortWith(sortFn) equals list.sortWith(sortFn)
        ) getOrElse(list, 0)
        
        // Add the tuple, increasing amount by one.
        table.put(newEntry._1, newEntry._2 + 1)
    }

    def prune(int: Int) : FrequentItemSet = {
        table = table.filter(_._2 >= int)

        this
    }
    
    override def equals(obj: scala.Any): Boolean = {
        (toString equals obj.toString) && obj.isInstanceOf[FrequentItemSet]
    }


    override def toString = {
        table.toList
            .sortWith(
                (a, b) => a._1.sortWith((a, b) => a < b).mkString("") < b._1.sortWith((a, b) => a < b).mkString("")
            )
            .map(entry => "[" + entry._1.sortWith((a, b) => a < b).mkString(", ") + "] => " + entry._2.toString).mkString("\n")
    }
}

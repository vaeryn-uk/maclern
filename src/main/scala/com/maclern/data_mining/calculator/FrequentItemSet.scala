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
    
    def add(list : List[Item]) : Unit = {
        // Find our existing entry, or initialise a new tuple with amount of zero.
        val newEntry: (List[Item], Int) = table.find(_._1 equals list) getOrElse(list, 0)
        // Add the tuple, increasing amount by one.
        table.put(newEntry._1, newEntry._2 + 1)
    }
    
    override def equals(obj: scala.Any): Boolean =
        // Is same type
        obj.isInstanceOf[FrequentItemSet] &&
        // All elements are in other with same amount
        table.forall(x => obj.asInstanceOf[FrequentItemSet].table exists (y => (y._1 equals x._1) && (y._2 equals x._2))) &&
        // Have the same amount of elements
        (obj.asInstanceOf[FrequentItemSet].table.size equals table.size)
    
}

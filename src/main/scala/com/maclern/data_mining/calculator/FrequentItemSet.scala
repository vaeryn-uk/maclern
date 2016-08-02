package com.maclern.data_mining.calculator

import com.maclern.data_mining.Item

import scala.collection.mutable

/**
  * Created by josh on 30/07/16.
  */
class FrequentItemSet(var table: mutable.HashMap[List[Item], Int])
{
    
    override def equals(obj: scala.Any): Boolean =
        // Is same type
        obj.isInstanceOf[FrequentItemSet] &&
        // All elements are in other with same amount
        table.forall(x => obj.asInstanceOf[FrequentItemSet].table exists (y => (y._1 equals x._1) && (y._2 equals x._2))) &&
        // Have the same amount of elements
        (obj.asInstanceOf[FrequentItemSet].table.size equals table.size)
    
}

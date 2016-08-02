package com.maclern.data_mining.calculator

import com.maclern.data_mining.{Item, Transaction, TransactionList}
import com.maclern.data_mining.{Transaction, TransactionList}

import scala.collection.mutable

/**
  * Created by josh on 30/07/16.
  */
object AprioriCalculator extends FrequentlyUsedItemsCalculator
{
    def apply(data: TransactionList, threshold : Int): FrequentItemSet = {


        var itemSet = firstIteration(data)
        println(itemSet.table.toString())
        itemSet = this.removeUnderThreshHold(itemSet, threshold)
        println(itemSet.table.toString())
        itemSet
//        this.iterate(itemSet, threshold, 2)
    }



    private def removeUnderThreshHold(itemList : FrequentItemSet, threshold : Int) = {
        new FrequentItemSet(itemList.table.filter(_._2 >= threshold ))
    }

    private def iterate(itemSet : FrequentItemSet, threshold : Int, k : Int) : FrequentItemSet = {
        val itemCollection = new mutable.HashMap[List[Item], Int]

//        var count = itemSet.table.size
//        for (i <- 0 to count) {
//            itemSet.table.iterator.next()[i]
//        }

        itemSet.table.foreach(_._1.foreach((item : Item) => {
            itemCollection.put(List(item), 0)
        }))
        this.removeUnderThreshHold(itemSet, threshold)
        itemSet
    }




    private def firstIteration(data: TransactionList): FrequentItemSet = {
        val set = new FrequentItemSet()
        data.transactions.foreach (_.items.foreach((item : Item) => set.add(List(item))))
        set
    }
}

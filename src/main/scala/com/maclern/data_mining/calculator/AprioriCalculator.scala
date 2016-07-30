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
        this.removeUnderThreshHold(itemSet, threshold)
        itemSet
    }

    private def removeUnderThreshHold(itemList : FrequentItemSet, threshold : Int) = {
        itemList.table.filter(_._2 >= threshold )
    }


    private def firstIteration(data: TransactionList): FrequentItemSet = {

        var itemCollection = new mutable.HashMap[List[Item], Int]

        data.transactions.foreach (_.items.foreach(
                    (item : Item) => {
                    var value: Int = 0
                    if (itemCollection.contains(List(item))) {
                        value = itemCollection(List(item))
                    }
                    itemCollection.put(List(item), value + 1)
                }
        )
        )

        new FrequentItemSet(itemCollection)
    }
}

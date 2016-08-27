package com.maclern.data_mining.calculator

import com.maclern.data_mining.{Item, TransactionList}

/**
  * Calculates frequently appearing items in a set.
  *
  * https://en.wikipedia.org/wiki/Apriori_algorithm
  */
object AprioriCalculator extends FrequentlyUsedItemsCalculator
{
    def apply(data: TransactionList, threshold : Int): FrequentItemSet = {
        removeUnderThreshold(firstIteration(data), threshold)
    }
    
    private def removeUnderThreshold(itemList : FrequentItemSet, threshold : Int) : FrequentItemSet = {
        new FrequentItemSet(itemList.table.filter(_._2 >= threshold ))
    }
    
    private def firstIteration(data: TransactionList): FrequentItemSet = {
        val set = new FrequentItemSet()
        data.transactions.foreach (_.items.foreach((item : Item) => set.add(List(item))))
        set
    }
}

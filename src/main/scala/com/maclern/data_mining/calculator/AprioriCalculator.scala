package com.maclern.data_mining.calculator

import com.maclern.data_mining.TransactionList
import com.maclern.util.Bool

/**
  * Calculates frequently appearing items in a set.
  *
  * https://en.wikipedia.org/wiki/Apriori_algorithm
  */
object AprioriCalculator extends FrequentlyUsedItemsCalculator
{
    def apply(data: TransactionList, threshold : Int): FrequentItemSet = {
        var frequentItemSet = new FrequentItemSet()
        var candidateSet : FrequentItemSet = null
        var k = 1

        data.transactions.foreach(t => t.items.foreach(i => frequentItemSet.add(List(i))))

        while (frequentItemSet.table.nonEmpty) {
            candidateSet = iteration(frequentItemSet, k, data).prune(threshold)

            if (candidateSet.table.isEmpty) {
                return frequentItemSet
            }

            frequentItemSet = candidateSet
            k += 1
        }

        frequentItemSet
    }

    private def iteration(
                                       frequentItemSet: FrequentItemSet,
                                       k: Integer,
                                       transactions: TransactionList
                                   ) : FrequentItemSet = {
        // 1. Get combinations
        // 2. For each combination, how many transaction does that appear in?
        // 3. Return combinations with occurrence count in FrequentItemSet.

        val relevantItems = frequentItemSet.table.keys.toList.flatten
        val newItemSet = new FrequentItemSet()

        relevantItems.combinations(k).toList.foreach(combination => {
            transactions.transactions.foreach(t => {
                Bool(t.contains(combination))->(() => newItemSet.add(combination))
            })
        })

        newItemSet
    }
}

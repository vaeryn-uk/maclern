package com.maclern.data_mining.calculator

import com.maclern.data_mining.{Item, Transaction, TransactionList}

/**
  * Calculates frequently appearing items in a set.
  *
  * https://en.wikipedia.org/wiki/Apriori_algorithm
  */
object AprioriCalculator extends FrequentlyUsedItemsCalculator
{
    var threshold: Integer = 0

    def apply(data: TransactionList, threshold : Int): FrequentItemSet = {

        this.threshold = threshold
        val items = data.transactions map (t => t.items)
        val frequentItemSet = removeUnderThreshold(firstIteration(items.iterator), threshold)

        val k = 2

        candidateGeneration(frequentItemSet, k)
        frequentItemSet
    }
    
    private def removeUnderThreshold(itemList : FrequentItemSet, threshold : Int) : FrequentItemSet = {
        new FrequentItemSet(itemList.table.filter(_._2 >= threshold ))
    }
    
    private def firstIteration(data: Iterator[List[Item]]): FrequentItemSet = {
        val set = new FrequentItemSet()
        data.foreach (_.foreach((item : Item) => set.add(List(item))))
        set
    }

    private def findFrequentItemSet(data: Iterator[List[Item]]): FrequentItemSet = {
        val set = new FrequentItemSet()
        data.foreach ( list => set.add(list) )
        set
    }

    private def candidateGeneration(frequentItemSet: FrequentItemSet, k: Integer) : Unit = {

        frequentItemSet.table.foreach { println }
        val combinatedList = frequentItemSet.table.keys.toList.combinations(k)
        println ("combinatedList")
        combinatedList.foreach { println }
        val items = combinatedList map (_.map (list => list.head) )

        println ("combinatedList casted to items")
        items.foreach { println }

        val frecuent = findFrequentItemSet(items)
        println ("frecuent item set")
        frecuent.table.foreach { println }

        val frecuentItemSet = removeUnderThreshold(findFrequentItemSet(items), threshold)
        println ("candidate generation")

        frecuentItemSet.table.foreach { println }
    }
}

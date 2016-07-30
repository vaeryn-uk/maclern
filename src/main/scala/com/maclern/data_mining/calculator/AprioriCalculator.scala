package com.maclern.data_mining.calculator

import com.maclern.data_mining.{Item, Transaction, TransactionList}
import com.maclern.data_mining.{Transaction, TransactionList}

import scala.collection.mutable

/**
  * Created by josh on 30/07/16.
  */
object AprioriCalculator extends FrequentlyUsedItemsCalculator
{
    def apply(data: TransactionList): FrequentItemSet = {

        var itemCollection = new mutable.HashMap[Item, Int]

        data.transactions.foreach(
            (transaction : Transaction) =>
                transaction.items.foreach(
                    (item : Item) => {
                        var value: Int = 0
                        if (itemCollection.contains(item)) {
                            value = itemCollection(item)
                        }
                        itemCollection.put(item, value+1)
                    }
                )
        )

        new FrequentItemSet(new mutable.HashMap[List[Item], Int])
//        var itemCollection = new mutable.HashTable[]

//        new FrequentItemSet(itemCollection);
    }

}

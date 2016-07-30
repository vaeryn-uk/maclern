package maclern.apriori

import scala.collection.mutable

/**
  * Created by josh on 30/07/16.
  */
class Algorithm(data: TransactionList)
{

    override def toString: String = data.toString

    def occurrences: mutable.HashMap[Item, Int] = {

        var itemCollection = new mutable.HashMap[Item, Int]

        data.transactions.foreach(
            (transaction : Transaction) =>
                transaction.items.foreach(
                    (item : Item) => {
                        var value: Int = 0
                        if (itemCollection.contains(item)) {
                            value = itemCollection.get(item).get
                        }
                        itemCollection.put(item, value+1)
                    }
                )
        )

        itemCollection
//        var itemCollection = new mutable.HashTable[]

//        new FrequentItemSet(itemCollection);
    }

}

package com.maclern.data_mining

/**
  * A list of transactions.
  */
class TransactionList(val transactions : List[Transaction]) {
    private val sortBy = (a : Transaction, b : Transaction) => a < b
    
    override def toString: String = this.transactions.addString(new StringBuilder, "\n").mkString
    
    override def equals(obj: scala.Any): Boolean =
        obj.isInstanceOf[TransactionList] && obj.asInstanceOf[TransactionList].transactions.sortWith(sortBy).equals(transactions sortWith sortBy)
}

object TransactionList {
    def apply(itemNames : List[List[String]]) : TransactionList = new TransactionList(
        itemNames map (names => new Transaction(names map (new Item(_))))
    )
}

package com.maclern.data_mining

/**
  * A customer's transaction.
  */
class Transaction(val items: List[Item]) {
    private val sortBy = (a : Item, b : Item) => a < b
    
    def >(transaction: Transaction) : Boolean = toString > transaction.toString
    def <(transaction: Transaction) : Boolean = toString < transaction.toString
    
    override def toString: String = items.addString(new StringBuilder, ", ").mkString
    
    override def equals(obj: scala.Any) : Boolean =
        obj.isInstanceOf[Transaction] && items.sortWith(sortBy).equals(obj.asInstanceOf[Transaction].items.sortWith(sortBy))
}

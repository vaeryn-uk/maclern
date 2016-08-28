package com.maclern.data_mining

import com.maclern.data_mining.exception.DuplicateItemException
import com.maclern.util.Bool

/**
  * A customer's transaction.
  *
  * A transaction is a list of items bought by a customer.
  * We don't care that a customer bought an item multiple times
  * in a transaction; this is simply the set of items that they
  * purchased. I.e. an item can only appear once in a transaction.
  */
class Transaction(val items: List[Item]) {
    
    // TODO: would be more natural to accept a Set of items to enforce non-duplicates.
    items.foreach(
        item =>
            Bool(items.exists(prospect => prospect.equals(item) && !(prospect eq item)))
                .implies(() => throw new DuplicateItemException )
    )
    
    private val sortBy = (a : Item, b : Item) => a < b
    
    def >(transaction: Transaction) : Boolean = toString > transaction.toString
    def <(transaction: Transaction) : Boolean = toString < transaction.toString
    
    override def toString: String = items.addString(new StringBuilder, ", ").mkString
    
    override def equals(obj: scala.Any) : Boolean =
        obj.isInstanceOf[Transaction] && items.sortWith(sortBy).equals(obj.asInstanceOf[Transaction].items.sortWith(sortBy))

    def contains(prospectItems : List[Item]) : Boolean = {
        items.count(prospectItems.contains(_)) equals prospectItems.length
    }
}

package com.maclern.data_mining

/**
  * A customer's transaction.
  */
class Transaction(val items: List[Item]) {
    override def toString: String = items.addString(new StringBuilder, ", ").mkString
}

package com.maclern.apriori

/**
  * A list of transactions.
  */
class TransactionList(val transactions: List[Transaction]) {

    override def toString: String = this.transactions.addString(new StringBuilder, "\n").mkString
}

package maclern.apriori

/**
  * A list of transactions.
  */
class TransactionList(transactions: List[Transaction]) {
    override def toString: String = transactions.addString(new StringBuilder, "\n").mkString
}

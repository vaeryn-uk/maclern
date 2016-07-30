/**
  * A customer's transaction.
  */
class Transaction(items: List[Item]) {
    override def toString: String = items.addString(new StringBuilder, ", ").mkString
}

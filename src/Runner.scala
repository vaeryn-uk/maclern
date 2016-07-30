/**
  * Created by andy on 30/07/16.
  */
object Runner {
    def main(args: Array[String]): Unit = {
        
        val apple = new Item("apple")
        val banana = new Item("banana")
        val orange = new Item("orange")
        
        val transactionList = new TransactionList(List(
            new Transaction(List(apple, banana, orange)),
            new Transaction(List(apple)),
            new Transaction(List(apple, orange))
        ))
        
        println(transactionList)
    }
}

package com.maclern.data_mining.loader

import java.io.FileNotFoundException

import com.maclern.data_mining.loader.exception.{InvalidLoaderException,LoadException}
import com.maclern.data_mining.{Item, Transaction, TransactionList}
import com.maclern.util.Bool

import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Loads a from a file, where each item is listed in a transaction separated by some separator,
  * then transactions separated with another.
  *
  * A comma, line separated file might look like:
  * item1, item2, item3
  * item2, item3
  * item1, item3, item4
  */
class DelimitedFileLoader(
                             val itemSeparator: Char = ',',
                             val transactionSeparator: Char = '\n',
                             val file : String
                         ) extends TransactionLoader
{
    Bool(itemSeparator equals transactionSeparator) -> (() => throw new InvalidLoaderException)
    
    override def apply(): TransactionList = {
        var source : Source = null
        try {
            source = Source.fromFile(file)
        } catch {
            case e: FileNotFoundException => {
                println (e.getMessage)
                throw new LoadException(cause = e)
            }
        }
        val transactions : ListBuffer[Transaction] = new ListBuffer[Transaction]()
        val currentTransaction : ListBuffer[Item] = new ListBuffer[Item]()
        var currentChar : Char = '\0'
        var currentItem : String = ""
        var endOfItem = false
        var endOfTransaction = false
        
        while (source.hasNext) {
            currentChar = source.next
            
            endOfItem = currentChar equals itemSeparator
            endOfTransaction = currentChar equals transactionSeparator
            
            if (endOfItem || endOfTransaction && (currentItem.length > 0)) {
                currentTransaction += new Item(currentItem)
                currentItem = ""
            }
            
            if (endOfTransaction) {
                transactions += new Transaction(currentTransaction.toList)
                currentTransaction.clear()
            }
            
            if (!endOfItem && !endOfTransaction) {
                currentItem += currentChar
            }
        }
        
        if (currentItem.nonEmpty) {
            currentTransaction += new Item(currentItem)
        }
        
        if (currentTransaction.nonEmpty) {
            transactions += new Transaction(currentTransaction.toList)
        }
        
        new TransactionList(transactions.toList)
    }
}

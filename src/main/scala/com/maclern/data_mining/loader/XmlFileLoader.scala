package com.maclern.data_mining.loader

import com.maclern.data_mining.loader.exception.LoadException
import com.maclern.data_mining.{Item, Transaction, TransactionList}
import com.maclern.util.Bool

import scala.collection.mutable.ListBuffer
import scala.xml.{Elem, XML};

/**
  * Loads a transaction list from an XML file.
  *
  * Expected format:
  *
  * <transaction-list>
  *     <transaction>
  *         <item>Item 1</item>
  *         <item>Item 2</item>
  *         <item>Item 3</item>
  *     </transaction>
  *     <transaction>
  *         <item>Item 4</item>
  *         <item>Item 5</item>
  *         <item>Item 6</item>
  *     </transaction>
  *     <transaction>
  *         <item>Item 7</item>
  *         <item>Item 8</item>
  *     </transaction>
  * </transaction-list>
  */
class XmlFileLoader(val file : String) extends TransactionLoader
{
    override def apply(): TransactionList = {
        val root = XML.loadFile(file)
        var currentChar : Char = '\0'
        var currentItem : String = ""
        var endOfItem = false
        var endOfTransaction = false
        
        Bool(root.label != "transaction-list")->(
            () => throw new LoadException("Expected XML root element 'transaction-list'")
        )
        
        val transactions : ListBuffer[Transaction] = new ListBuffer[Transaction]()
        
        root \ "transaction" foreach((transaction) => {
            val currentTransaction : ListBuffer[Item] = new ListBuffer[Item]()
            
            transaction \ "item" foreach((item) => {
                currentTransaction += new Item(item.text)
            })
            
            transactions += new Transaction(currentTransaction.toList)
        })
        
        new TransactionList(transactions.toList)
    }
}

package com.maclern.data_mining.loader

import com.maclern.data_mining.TransactionList
import com.maclern.data_mining.loader.exception.{InvalidLoaderException,LoadException}
import org.scalatest.{FlatSpec, Matchers}

class DelimitedFileLoaderSpec extends FlatSpec with Matchers {
    val list1Expectation = TransactionList(List(
        List("item1", "item2", "item3"),
        List("item1", "item3"),
        List("item4", "item2", "item5")
    ))
    
    val list2Expectation = TransactionList(List(
        List("item1", "item2", "item3"),
        List("item1", "item2"),
        List("item1")
    ))
    
    "Loader" should "load a comma-separated, line-separated file" in {
        val loader = new DelimitedFileLoader(
            file = getClass.getResource("/transaction-lists/list1").getPath
        )
        
        loader() should equal (list1Expectation)
    }
    
    "Loader" should "load a semi-colon-separated, colon-separated file" in {
        val loader = new DelimitedFileLoader(
            file = getClass.getResource("/transaction-lists/list2").getPath,
            itemSeparator = ';',
            transactionSeparator = ':'
        )
    
        loader() should equal (list2Expectation)
    }
    
    "Load" should "not allow the same separator for items and transactions" in {
        intercept[InvalidLoaderException] {
            new DelimitedFileLoader(',', ',', "")
        }
    }
    
    "Load" should "not throw if the file doesn't exist" in {
        val loader = new DelimitedFileLoader(file = "not-exists")
        intercept[LoadException] {
            loader()
        }
    }
    
    "Load" should "handle an empty list" in {
        val loader = new DelimitedFileLoader(
            file = getClass.getResource("/transaction-lists/empty-list").getPath
        )
        
        loader() should equal(TransactionList(List()))
    }
    
    "Load" should "handle empty transactions" in {
        val loader = new DelimitedFileLoader(
            file = getClass.getResource("/transaction-lists/empty-transactions").getPath
        )
        
        loader() should equal(TransactionList(List(
            List("item1"),
            List(),
            List("item2"),
            List(),
            List("item3", "item4")
        )))
    }
}

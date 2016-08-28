package com.maclern.data_mining.loader

import com.maclern.data_mining.TransactionList
import com.maclern.data_mining.loader.exception.{InvalidLoaderException, LoadException}
import org.scalatest.{FlatSpec, Matchers}

class XmlFileLoaderSpec extends FlatSpec with Matchers {
    val list1Expectation = TransactionList(List(
        List("Item 1", "Item 2", "Item 3"),
        List("Item 4", "Item 5", "Item 6"),
        List("Item 7", "Item 8")
    ))
    
    "Loader" should "load an XML file with transactions" in {
        val loader = new XmlFileLoader(
            file = getClass.getResource("/transaction-lists/list1.xml").getPath
        )
        
        loader() should equal (list1Expectation)
    }
}

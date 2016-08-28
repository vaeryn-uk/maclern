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
    
    val extraElementsExpectation = TransactionList(List(
        List("Item 1", "Item 2", "Item 3")
    ))
    
    val emptyListExpectation = TransactionList(List())
    
    val emptyTransactionExpectation = TransactionList(List(List()))
    
    val loadWithXmlFile = (file : String) => {
        new XmlFileLoader(file = getClass.getResource("/transaction-lists/xml/" + file).getPath)
    }
    
    "Loader" should "load an XML file with transactions" in {
        loadWithXmlFile("list1.xml")() should equal (list1Expectation)
    }
    
    "Loader" should "load an XML file with extra elements" in {
        loadWithXmlFile("extra-elements.xml")() should equal (extraElementsExpectation)
    }
    
    "Loader" should "load an XML file with an empty list" in {
        loadWithXmlFile("empty-list.xml")() should equal (emptyListExpectation)
    }
    
    "Loader" should "load an XML file with an empty transaction" in {
        loadWithXmlFile("empty-transaction.xml")() should equal (emptyTransactionExpectation)
    }
    
    "Loader" should "throw with an invalid XML file" in {
        val loader: XmlFileLoader = loadWithXmlFile("not-transaction-list.xml")
        
        intercept[LoadException] (loader())
    }
}

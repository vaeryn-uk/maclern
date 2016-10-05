package com.maclern.data_mining

import com.maclern.data_mining.calculator.AprioriCalculator
import com.maclern.data_mining.loader.DelimitedFileLoader

/**
  * Created by josh on 28/08/16.
  */
object Main {
    def main(args:Array[String]) : Unit = {
        println("Loading file...")
        val loader = new DelimitedFileLoader(file = "/tmp/data-gen/half-data.csv")
        println("Loaded file")
        println(AprioriCalculator(loader(), 17))
    }
}
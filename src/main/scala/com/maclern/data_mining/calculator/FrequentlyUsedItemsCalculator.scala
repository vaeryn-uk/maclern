package com.maclern.data_mining.calculator

import com.maclern.data_mining.TransactionList

/**
  * Created by andy on 30/07/16.
  */
trait FrequentlyUsedItemsCalculator {
    def apply(data: TransactionList, threshold : Int): FrequentItemSet
}

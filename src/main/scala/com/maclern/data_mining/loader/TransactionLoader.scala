package com.maclern.data_mining.loader

import com.maclern.data_mining.TransactionList

/**
  * Loads transactions.
  */
trait TransactionLoader {
    def apply() : TransactionList
}

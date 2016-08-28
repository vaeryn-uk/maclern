package com.maclern.data_mining.exception

/**
  * When an item appears more than once, but it should not.
  */
class DuplicateItemException(val message : String) extends RuntimeException(message) {

}

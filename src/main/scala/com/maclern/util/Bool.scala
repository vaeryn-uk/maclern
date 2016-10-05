package com.maclern.util

/**
  * Wrapper around a native Scala Boolean, offering extended functionality.
  */
class Bool(val value : Boolean) {
    /**
      * Invokes p iff bool is true.
      *
      * @param p Function to invoke
      */
    def implies(p : () => Unit) : Bool = {
        if (value) {
            p()
        }
        this
    }
    
    /**
      * Alias for implies.
      *
      * @see Bool.implies
      *
      * @param p Function to invoke
      */
    def ->(p : () => Unit) : Bool = implies(p)
}

object Bool {
    def apply(value: Boolean) : Bool = new Bool(value)
}

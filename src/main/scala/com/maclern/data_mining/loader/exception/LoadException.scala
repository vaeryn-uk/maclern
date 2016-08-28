package com.maclern.data_mining.loader.exception

/**
  * When transactions could not be loaded.
  */
class LoadException(val message : String = "", val cause : Throwable = null) extends RuntimeException
{
    
}

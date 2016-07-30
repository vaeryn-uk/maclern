package com.maclern.data_mining

/**
  * An item for purchase.
  */
class Item(val name: String) {
    
    def >(item: Item) = name > item.name
    def <(item: Item) = name < item.name
    
    override def toString: String = name
    
    override def equals(obj: scala.Any): Boolean = obj.isInstanceOf[Item] && this.name.equals(obj.asInstanceOf[Item].name)
}

package com.localiza.movedriver.base.listeners

interface OnItemClickListener<T> {

    fun onIndexItemClick(item: T, position: Int) {}

    fun onItemClick(item: T, itemData: T) {}

    fun onItemClick(item: T) {}

}
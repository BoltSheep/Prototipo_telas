package com.example.prototipodenovamovimentao.base.listeners

interface OnButtonClickListener {

    fun onClickPositive() {}

    fun onClickNegative() {}

    fun onClickFilterByType(displacementTypeList: List<String>) {}

    fun onClickFilterByPeriod(startDate: String?, finishDate: String?) {}

    fun onClickSelectPlace(checkedRadioButtonId: Int) {}

    fun onClearSelectedPlace() {}

}
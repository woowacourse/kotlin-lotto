package lotto.model

import lotto.view.ERROR_NOT_DIVIDED

class Money(val money: Int) {
    init {
        require(money % LOTTO_PRICE == 0) { println(ERROR_NOT_DIVIDED) }
    }

    fun getNumberOfLotto(): Int {
        return this.money / LOTTO_PRICE
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}

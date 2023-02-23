package lotto.model

import lotto.view.ERROR_NOT_ENOUGH_MONEY

class UserLottoCount(
    money: Int,
    val manual: Int
) {
    val all: Int
    val auto: Int
        get() = all - manual

    init {
        all = money / LOTTO_PRICE
        require(all >= manual) { ERROR_NOT_ENOUGH_MONEY }
    }

    companion object {
        private const val LOTTO_PRICE: Int = 1000
    }
}

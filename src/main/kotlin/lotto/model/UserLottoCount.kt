package lotto.model

import lotto.view.ERROR_NOT_ENOUGH_MONEY

class UserLottoCount private constructor(
    private val all: Int,
    val passive: Int
) {
    val auto: Int
        get() = all - passive

    companion object {
        private const val LOTTO_PRICE: Int = 1000

        fun create(money: Int, numberOfPassive: Int): UserLottoCount {
            val number = money / LOTTO_PRICE
            if (number < numberOfPassive) {
                throw IllegalArgumentException(ERROR_NOT_ENOUGH_MONEY)
            }
            return UserLottoCount(number, numberOfPassive)
        }
    }
}

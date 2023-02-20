package lotto.model

import lotto.view.ERROR_NOT_ENOUGH_MONEY

class UserLottoCount private constructor(
    private val all: Int,
    val passive: Int
) {
    val auto: Int
        get() = all - passive

    companion object {
        fun create(number: Int, numberOfPassive: Int): UserLottoCount {
            if (number < numberOfPassive) {
                throw IllegalArgumentException(ERROR_NOT_ENOUGH_MONEY)
            }
            return UserLottoCount(number, numberOfPassive)
        }
    }
}

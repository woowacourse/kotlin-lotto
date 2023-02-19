package domain

import domain.LottoStore.Companion.LOTTO_PRICE

data class Count private constructor(private val number: Int) {
    constructor(number: Int, amount: Amount) : this(number) {
        require(number > 0) { ERROR_MINUS.format(number) }
        require((amount / LOTTO_PRICE).toInt() >= number) { ERROR_NUMBER_RANGE.format(number) }
    }

    companion object {
        private const val ERROR_MINUS = "0 이상의 숫자를 입력해야 합니다.\n잘못된 값 : %d"
        private const val ERROR_NUMBER_RANGE = "구입 가능한 로또 장수를 초과합니다.\n잘못된 값 : %d"
    }
}

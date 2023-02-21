package domain

import domain.LottoStore.Companion.LOTTO_PRICE

data class Count private constructor(val count: Int) {
    constructor(count: Int, amount: Amount) : this(count) {
        require(count >= 0) { ERROR_MINUS.format(count) }
        require((amount.amount / LOTTO_PRICE) >= count) { ERROR_NUMBER_RANGE.format(count) }
    }

    fun toInt() = count

    companion object {
        private const val ERROR_MINUS = "0 이상의 숫자를 입력해야 합니다.\n잘못된 값 : %d"
        private const val ERROR_NUMBER_RANGE = "구입 가능한 로또 장수를 초과합니다.\n잘못된 값 : %d"
    }
}

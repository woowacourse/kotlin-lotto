package model

class Money(val value: Int) {
    init {
        require(value >= LOTTO_PRICE) { MINIMUM_VALUE_EXCEPTION_MESSAGE }
        require(value % LOTTO_PRICE == 0) { INVALID_UNIT_EXCEPTION_MESSAGE }
    }

    companion object {
        const val LOTTO_PRICE = 1000
        const val MINIMUM_VALUE_EXCEPTION_MESSAGE = "구입 금액은 ${LOTTO_PRICE}원 이상이어야 합니다."
        const val INVALID_UNIT_EXCEPTION_MESSAGE = "구입 금액은 ${LOTTO_PRICE}원 단위여야 합니다."
    }
}

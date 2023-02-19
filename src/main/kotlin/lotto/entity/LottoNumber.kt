package lotto.entity

data class LottoNumber(val value: Int) {
    init {
        require(value in MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER) { ERROR_MESSAGE_LOTTO_RANGE_1_TO_45 }
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
        const val ERROR_MESSAGE_LOTTO_RANGE_1_TO_45 = "로또 번호는 1에서 45 사이의 숫자여야 합니다"
    }
}

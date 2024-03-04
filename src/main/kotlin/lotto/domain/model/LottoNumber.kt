package lotto.domain.model

data class LottoNumber(val value: Int) {
    init {
        require(value in MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER) { NUMBER_RANGE_EXCEPTION_MESSAGE }
    }

    companion object {
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
        private const val NUMBER_RANGE_EXCEPTION_MESSAGE = "로또 번호는 $MINIMUM_LOTTO_NUMBER ~ $MAXIMUM_LOTTO_NUMBER 사이의 정수여야 합니다."
    }
}

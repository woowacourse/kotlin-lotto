package lotto.domain.model

data class LottoNumber(val number: Int) {
    init {
        require(number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) { INVALID_LOTTO_NUMBER_RANGE_MESSAGE }
    }

    private companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        const val INVALID_LOTTO_NUMBER_RANGE_MESSAGE = "로또의 각 번호는 1~45이하의 숫자를 가진다."
    }
}

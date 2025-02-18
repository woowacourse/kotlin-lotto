package lotto.domain.model

class LottoNumber(val number: Int) {
    init {
        require(number in INVALID_LOTTO_MIN_NUMBER..INVALID_LOTTO_MAX_NUMBER) { INVALID_LOTTO_NUMBER_RANGE_MESSAGE }
    }

    private companion object {
        const val INVALID_LOTTO_MIN_NUMBER = 1
        const val INVALID_LOTTO_MAX_NUMBER = 45
        const val INVALID_LOTTO_NUMBER_RANGE_MESSAGE = "로또의 각 번호는 1~45이하의 숫자를 가진다."
    }
}

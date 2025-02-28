package lotto.domain

@JvmInline
value class LottoNumber(val number: Int) {
    init {
        validateLottoNumberRange()
    }

    private fun validateLottoNumberRange() {
        require(number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) { ERROR_OUT_OF_LOTTO_NUMBER_RANGE }
    }

    companion object {
        const val ERROR_OUT_OF_LOTTO_NUMBER_RANGE = "[ERROR] 로또 번호는 1~45 사이어야 합니다."
        const val MIN_LOTTO_NUMBER = 1
        const val MAX_LOTTO_NUMBER = 45
    }
}

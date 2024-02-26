package lotto.model

@JvmInline
value class LottoNumber(val number: Int) {
    init {
        require(number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER)
    }

    companion object {
        const val MIN_LOTTO_NUMBER = 1
        const val MAX_LOTTO_NUMBER = 45
    }
}

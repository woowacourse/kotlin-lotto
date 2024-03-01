package lotto.model

@JvmInline
value class LottoNumber(val number: Int) {
    init {
        require(number in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER) {
            "${LOTTO_MIN_NUMBER}부터 ${LOTTO_MAX_NUMBER}사이의 숫자를 입력해 주세요."
        }
    }

    override fun toString(): String = number.toString()

    companion object {
        private const val LOTTO_MIN_NUMBER: Int = 1
        private const val LOTTO_MAX_NUMBER: Int = 45
    }
}

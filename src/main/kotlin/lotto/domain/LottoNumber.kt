package lotto.domain

class LottoNumber private constructor(
    val value: Int,
) {
    init {
        require(value in RANGE) { ERROR_MESSAGE_LOTTO_NUMBER_NOT_IN_RANGE }
    }

    companion object {
        fun from(value: Int): LottoNumber {
            return NUMBER.getOrPut(value) { LottoNumber(value) }
        }

        private const val MIN = 1
        private const val MAX = 45
        val RANGE = (MIN..MAX)
        private val NUMBER: MutableMap<Int, LottoNumber> = mutableMapOf()
        private const val ERROR_MESSAGE_LOTTO_NUMBER_NOT_IN_RANGE = "로또는 1부터 45 사이의 숫자만 가질 수 있습니다."
    }
}

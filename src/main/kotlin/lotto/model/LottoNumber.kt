package lotto.model

class LottoNumber private constructor(private val number: Int) {
    override fun toString() = number.toString()

    companion object {
        const val MIN_RANGE = 1
        const val MAX_RANGE = 45
        private const val INVALID_RANGE = "${MIN_RANGE}부터 $MAX_RANGE 사이의 숫자를 입력해 주세요."
        private val lottoNumbers = mutableMapOf<Int, LottoNumber>()

        fun from(number: Int): LottoNumber {
            require(number in MIN_RANGE..MAX_RANGE) { INVALID_RANGE }
            if (!lottoNumbers.contains(number)) {
                lottoNumbers[number] = LottoNumber(number)
            }

            return lottoNumbers[number] ?: throw IllegalArgumentException()
        }
    }
}

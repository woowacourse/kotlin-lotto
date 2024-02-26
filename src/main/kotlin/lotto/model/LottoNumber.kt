package lotto.model

data class LottoNumber(private val number: Int) {
    init {
        require(number in MIN_RANGE..MAX_RANGE) { INVALID_RANGE }
    }

    override fun toString() = number.toString()

    companion object {
        const val MIN_RANGE = 1
        const val MAX_RANGE = 45
        private const val INVALID_RANGE = "${MIN_RANGE}부터 $MAX_RANGE 사이의 숫자를 입력해 주세요."
    }
}

package lotto

class LottoNumber(
    private val number: Int,
) {
    init {
        require(number in MIN_NUMBER..MAX_NUMBER) { ERROR_OUT_OF_RANGE_NUMBERS }
    }

    companion object {
        private const val ERROR_OUT_OF_RANGE_NUMBERS = "로또 번호는 1 ~ 45 사이의 숫자입니다."
        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = 45
    }

    override fun equals(other: Any?): Boolean {
        other as LottoNumber
        return number == other.number
    }

    override fun hashCode(): Int = number
}

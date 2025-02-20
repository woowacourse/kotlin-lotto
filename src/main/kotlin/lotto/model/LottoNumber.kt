package lotto.model

class LottoNumber(
    val number: Int,
) {
    init {
        require(number in MIN_NUMBER..MAX_NUMBER) { ERROR_OUT_OF_RANGE_NUMBERS }
    }

    override fun equals(other: Any?): Boolean {
        other as LottoNumber
        return number == other.number
    }

    override fun hashCode(): Int = number

    companion object {
        private const val ERROR_OUT_OF_RANGE_NUMBERS = "로또 번호는 1 ~ 45 사이의 숫자입니다."
        const val MIN_NUMBER = 1
        const val MAX_NUMBER = 45
    }
}

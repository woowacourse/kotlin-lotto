package lotto.domain

class LottoAmount(private val value: Int) {
    init {
        require(value >= 0) { MANUAL_LOTTO_AMOUNT_ERROR_MESSAGE }
    }

    fun toInt(): Int = value

    fun isGreaterThan(other: LottoAmount): Boolean {
        return this.value > other.value
    }

    companion object {
        private const val MANUAL_LOTTO_AMOUNT_ERROR_MESSAGE = "수동으로 구매할 로또의 수는 정수로 입력해야 한다"
    }
}

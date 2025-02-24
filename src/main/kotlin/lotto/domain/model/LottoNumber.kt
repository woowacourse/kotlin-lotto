package lotto.domain.model

data class LottoNumber(val number: Int) : Comparable<LottoNumber> {

    init {
        require(number in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER) {
            INVALID_LOTTO_NUMBER_RANGE_MESSAGE.format(number)
        }
    }

    override fun compareTo(other: LottoNumber): Int {
        return this.number - other.number
    }

    override fun toString(): String {
        return this.number.toString()
    }

    companion object {
        const val LOTTO_MIN_NUMBER = 1
        const val LOTTO_MAX_NUMBER = 45
        private const val INVALID_LOTTO_NUMBER_RANGE_MESSAGE = "로또의 번호가 %d 입니다. 로또의 각 번호는 1~45이하의 숫자만 가집니다."
    }
}

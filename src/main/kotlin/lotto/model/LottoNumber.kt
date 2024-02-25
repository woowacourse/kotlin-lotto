package lotto.model

class LottoNumber(private val numbers: Set<Int>) {
    init {
        require(numbers.size == Lotto.LOTTO_LEN)
        require(numbers.all { it in Lotto.LOTTO_NUM_RANGE })
    }

    fun getNumbers(): Set<Int> {
        return numbers
    }
}

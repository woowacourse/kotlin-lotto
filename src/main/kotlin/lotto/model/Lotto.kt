package lotto.model
import lotto.util.Constant

class Lotto(private val numbers: Set<Int>) {
    init {
        require(numbers.size == Constant.LOTTO_LEN)
        require(numbers.all{ it in Constant.LOTTO_NUM_RANGE})
    }

    fun getNumbers(): Set<Int> {
        return numbers
    }

}
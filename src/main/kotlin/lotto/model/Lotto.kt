package lotto.model

import lotto.util.Constant

class Lotto(private val numbers: List<LottoNumber>) {
    init {
        require(numbers.toSet().size == Constant.LOTTO_SIZE)
        require(
            numbers.all { number ->
                number.getNumber() in Constant.MIN_LOTTO_NUMBER_RANGE..Constant.MAX_LOTTO_NUMBER_RANGE
            },
        )
    }

    fun getNumbers(): List<LottoNumber> = numbers
}

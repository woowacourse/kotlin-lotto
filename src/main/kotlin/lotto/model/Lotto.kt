package lotto.model

import lotto.util.Constant

class Lotto(private val numbers: List<LottoNumber>) {
    init {
        require(numbers.toSet().size == Constant.LOTTO_SIZE)
    }

    fun getNumbers(): List<LottoNumber> = numbers
}

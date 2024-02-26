package model

import util.Constant

class Lotto(private val numbers: List<LottoNumber>) {
    init {
        require(numbers.toSet().size == Constant.LOTTO_SIZE)
    }

    fun getNumbers(): List<LottoNumber> = numbers

    override fun toString(): String {
        val numbers = numbers.map { it.getNumber() }
        return numbers.joinToString(", ", "[", "]")
    }
}

package domain

class Lotto(val numbers: Set<LottoNumber>) {
    constructor(vararg numbers: Int) : this(numbers.map(::LottoNumber).toSet())

    init {
        require(numbers.size == LOTTO_SIZE) { ERROR_LOTTO_SIZE }
    }

    fun toList(): List<Int> {
        return numbers.map { LottoNumber ->
            LottoNumber.toInt()
        }
    }

    fun contains(number: LottoNumber): Boolean {
        return numbers.contains(number)
    }

    companion object {
        private const val LOTTO_SIZE = 6
        private const val ERROR_LOTTO_SIZE = "로또 번호는 6개여야 합니다."
    }
}

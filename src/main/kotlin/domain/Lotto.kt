package domain

data class Lotto(private val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_SIZE) { INPUT_LOTTO_SIZE_ERROR_MESSAGE }
    }

    constructor(vararg numbers: Int) : this(numbers.map { LottoNumber.from(it) }.sortedBy { it.number }.toSet())

    fun countSameLottoNumber(lotto: Lotto): Int {
        return numbers.count { lotto.numbers.contains(it) }
    }

    fun containsNumber(number: LottoNumber): Boolean {
        return numbers.contains(number)
    }

    companion object {
        const val LOTTO_SIZE = 6
        const val INPUT_LOTTO_SIZE_ERROR_MESSAGE = "로또 번호가 ${LOTTO_SIZE}개가 아닙니다"
    }
}

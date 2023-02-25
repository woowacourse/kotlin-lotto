package domain

open class Lotto(val numbers: List<LottoNumber>) : List<LottoNumber> by numbers {
    init {
        require(numbers.size == LOTTO_NUMBERS_COUNT) { LOTTO_NUMBERS_COUNT_ERROR }
        require(!isContainDuplicatedNumber()) { LOTTO_NUMBER_DUPLICATED_ERROR }
    }

    constructor(vararg numbers: Int) : this(numbers.map(::LottoNumber))

    private fun isContainDuplicatedNumber(): Boolean {
        return numbers.size != numbers.toSet().size
    }

    fun getCountOfMatch(lotto: Lotto): Int {
        return numbers.count { lotto.contains(it) }
    }

    companion object {
        const val LOTTO_NUMBERS_COUNT = 6
        const val LOTTO_NUMBERS_COUNT_ERROR = "[ERROR] 로또의 번호가 6개가 아닙니다."
        const val LOTTO_NUMBER_DUPLICATED_ERROR = "[ERROR] 중복된 로또 번호가 있습니다."
    }
}

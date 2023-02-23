package domain

data class Lotto(private val numbers: List<LottoNumber>) : List<LottoNumber> by numbers {
    init {
        require(numbers.size == LOTTO_SIZE) { INPUT_LOTTO_SIZE_ERROR_MESSAGE }
    }

    constructor(vararg numbers: Int) : this(numbers.map { LottoNumber.from(it) })

    fun countSameLottoNumber(lotto: Lotto): Int {
        return numbers.count { lotto.contains(it) }
    }

    fun hasBonusNumber(number: LottoNumber): Boolean {
        return numbers.contains(number)
    }

    companion object {
        const val LOTTO_SIZE = 6
        const val INPUT_LOTTO_SIZE_ERROR_MESSAGE = "당첨 번호가 ${LOTTO_SIZE}개가 아닙니다"
        const val INPUT_LOTTO_DUPLICATE_ERROR_MESSAGE = "당첨 번호가 중복되었습니다."
    }
}

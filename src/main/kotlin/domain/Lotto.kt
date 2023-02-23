package domain

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == 6) { LOTTO_SIZE_ERROR_MESSAGE }
        require(numbers.toSet().size == 6) { LOTTO_DUPLICATE_ERROR_MESSAGE }
    }
    constructor(vararg numbers: Int) : this(numbers.map { LottoNumber.from(it) })

    fun matchLotto(lotto: Lotto, bonusNumber: LottoNumber): Rank? =
        Rank.valueOf(countMatchNumber(lotto), hasBonusNumber(bonusNumber))

    private fun countMatchNumber(lotto: Lotto): Int {
        return numbers.count { lotto.numbers.contains(it) }
    }

    private fun hasBonusNumber(bonusNumber: LottoNumber): Boolean {
        return numbers.contains(bonusNumber)
    }

    companion object {
        private const val LOTTO_SIZE_ERROR_MESSAGE = "당첨 번호가 6개가 아닙니다"
        private const val LOTTO_DUPLICATE_ERROR_MESSAGE = "당첨 번호가 중복되었습니다."
    }
}

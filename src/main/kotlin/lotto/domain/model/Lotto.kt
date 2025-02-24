package lotto.domain.model

class Lotto(val numbers: Set<LottoNumber>) {
    constructor(numbers: List<Int>) : this(numbers.map { LottoNumber(it) }.toSet())

    init {
        require(numbers.size == LOTTO_NUMBER_SIZE) {
            INVALID_LOTTO_NUMBER_SIZE_MESSAGE.format(this)
        }
    }

    fun getLottoRank(
        winningLotto: Lotto,
        bonusNumber: LottoNumber,
    ): LottoRank {
        val matchCount = numbers.count { number -> winningLotto.numbers.contains(number) }
        val isMatchBonusNumber = numbers.contains(bonusNumber)
        return LottoRank.calculate(matchCount, isMatchBonusNumber)
    }

    override fun toString(): String {
        return this.numbers.toString()
    }

    companion object {
        const val LOTTO_PRICE = 1000
        private const val LOTTO_NUMBER_SIZE = 6
        private const val INVALID_LOTTO_NUMBER_SIZE_MESSAGE = "%s 중복을 제외한 로또 번호 입니다. 로또 번호는 6개여야 합니다."
    }
}

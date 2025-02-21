package lotto.domain.model

class WinningNumbers(private val winningLotto: Lotto, private val bonusNumber: LottoNumber) {
    constructor(numbers: List<Int>, bonusNumber: Int) : this(Lotto(numbers), LottoNumber(bonusNumber))

    init {
        require(winningLotto.numbers.contains(bonusNumber).not()) { DUPLICATE_WINNING_NUMBER_MESSAGE }
    }

    fun calculateLottoRanks(lottos: List<Lotto>): LottoRanks {
        return LottoRanks(lottos.map { lotto -> calculateLottoRank(lotto) })
    }

    private fun calculateLottoRank(lotto: Lotto): LottoRank {
        val matchCount = lotto.numbers.count { number -> winningLotto.numbers.contains(number) }
        val isMatchBonusNumber = lotto.numbers.contains(bonusNumber)
        return LottoRank.calculate(matchCount, isMatchBonusNumber)
    }

    private companion object {
        const val DUPLICATE_WINNING_NUMBER_MESSAGE = "보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}

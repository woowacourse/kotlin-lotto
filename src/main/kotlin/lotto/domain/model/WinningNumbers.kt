package lotto.domain.model

class WinningNumbers(private val winningLotto: Lotto, private val bonusNumber: LottoNumber) {
    constructor(numbers: List<Int>, bonusNumber: Int) : this(Lotto(numbers), LottoNumber(bonusNumber))

    init {
        val duplicateBonusNumber = winningLotto.numbers.find { it == bonusNumber }
        require(duplicateBonusNumber == null) { DUPLICATE_WINNING_NUMBER_MESSAGE.format(duplicateBonusNumber)}
    }

    fun calculateLottoRanks(lottos: List<Lotto>): LottoRanks {
        return LottoRanks(lottos.map { lotto -> calculateLottoRank(lotto) })
    }

    private fun calculateLottoRank(lotto: Lotto): LottoRank {
        val matchCount = lotto.getMatchCount(winningLotto)
        val isMatchBonusNumber = lotto.isMatchBonusNumber(bonusNumber)
        return LottoRank.calculate(matchCount, isMatchBonusNumber)
    }

    private companion object {
        const val DUPLICATE_WINNING_NUMBER_MESSAGE = "보너스 번호 %s은(는) 당첨 번호와 중복 될 수 없습니다."
    }
}

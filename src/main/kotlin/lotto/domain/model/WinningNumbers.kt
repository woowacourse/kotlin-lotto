package lotto.domain.model

class WinningNumbers(private val winningLotto: Lotto, private val bonusNumber: LottoNumber) {
    constructor(numbers: List<Int>, bonusNumber: Int) : this(Lotto(numbers), LottoNumber(bonusNumber))

    init {
        val duplicateBonusNumber = winningLotto.numbers.find { it == bonusNumber }
        require(duplicateBonusNumber == null) { DUPLICATE_WINNING_NUMBER_MESSAGE.format(duplicateBonusNumber) }
    }

    fun calculateLottoRanks(lottos: List<Lotto>): LottoRanks {
        val ranks = LottoRank.entries.associateWith { rank -> getLottoRankCount(lottos, rank) }
        return LottoRanks(ranks)
    }

    private fun getLottoRankCount(
        lottos: List<Lotto>,
        rank: LottoRank,
    ): Int {
        return lottos.count { lotto -> lotto.getLottoRank(winningLotto, bonusNumber) == rank }
    }

    private companion object {
        const val DUPLICATE_WINNING_NUMBER_MESSAGE = "보너스 번호 %s은(는) 당첨 번호와 중복 될 수 없습니다."
    }
}

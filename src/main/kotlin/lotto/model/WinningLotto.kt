package lotto.model

class WinningLotto(
    private val lotto: Lotto,
) {
    fun getRank(
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): Rank {
        val countOfMatch = lotto.countMatchWinningNumbers(winningNumbers)
        val matchBonus = lotto.isHaveBonusNumber(bonusNumber)

        lotto.validateLottoNumbersDuplicate(winningNumbers + listOf(bonusNumber))

        return Rank.fromMatchResult(countOfMatch, matchBonus)
    }
}

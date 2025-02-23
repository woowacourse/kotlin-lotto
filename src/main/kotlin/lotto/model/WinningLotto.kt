package lotto.model

class WinningLotto(
    private val lotto: Lotto,
) {
    fun getRank(
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): Rank {
        val countOfMatch = countMatchWinningNumbers(winningNumbers)
        val matchBonus = isHaveBonusNumber(bonusNumber)

        lotto.validateLottoNumbersDuplicate(winningNumbers.map { LottoNumber(it) } + listOf(LottoNumber(bonusNumber)))

        return Rank.fromMatchResult(countOfMatch, matchBonus)
    }

    private fun countMatchWinningNumbers(winningNumbers: List<Int>): Int {
        lotto.validateLottoNumbersCount(winningNumbers.map { LottoNumber(it) })

        return lotto.numbers.count { existNumber -> winningNumbers.contains(existNumber.number) }
    }

    private fun isHaveBonusNumber(bonusNumber: Int): Boolean = lotto.numbers.map { it.number }.contains(bonusNumber)
}

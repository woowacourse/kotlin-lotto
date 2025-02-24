package lotto.model

class WinningLotto(
    private val lotto: Lotto,
) {
    fun getRank(
        winningNumbers: Set<Int>,
        bonusNumber: Int,
    ): Rank {
        val countOfMatch = countMatchWinningNumbers(winningNumbers)
        val matchBonus = isHaveBonusNumber(bonusNumber)

        validateWinningNumbersBonusNumberDuplicate(winningNumbers, bonusNumber)

        return Rank.fromMatchResult(countOfMatch, matchBonus)
    }

    private fun countMatchWinningNumbers(winningNumbers: Set<Int>): Int {
        lotto.validateLottoNumbersCount(winningNumbers.map { LottoNumber(it) }.toSet())

        return lotto.numbers.count { existNumber -> winningNumbers.contains(existNumber.number) }
    }

    private fun validateWinningNumbersBonusNumberDuplicate(
        winningNumbers: Set<Int>,
        bonusNumber: Int,
    ) {
        require(!winningNumbers.contains(bonusNumber)) {
            "[ERROR] 당첨 번호와 보너스 번호는 중복될 수 없습니다."
        }
    }

    private fun isHaveBonusNumber(bonusNumber: Int): Boolean = lotto.numbers.map { it.number }.contains(bonusNumber)
}

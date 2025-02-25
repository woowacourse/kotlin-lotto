package lotto.model

class WinningLotto(
    private val lotto: Lotto,
) {
    fun getRank(
        winningNumbers: Set<LottoNumber>,
        bonusNumber: LottoNumber,
    ): Rank {
        validateWinningNumbersBonusNumberDuplicate(winningNumbers, bonusNumber)

        val countOfMatch = countMatchWinningNumbers(winningNumbers)
        val matchBonus = isHaveBonusNumber(bonusNumber)

        return Rank.fromMatchResult(countOfMatch, matchBonus)
    }

    private fun countMatchWinningNumbers(winningNumbers: Set<LottoNumber>): Int {
        lotto.validateLottoNumbersCount(winningNumbers)

        return lotto.numbers.count { existNumber -> winningNumbers.contains(existNumber) }
    }

    private fun validateWinningNumbersBonusNumberDuplicate(
        winningNumbers: Set<LottoNumber>,
        bonusNumber: LottoNumber,
    ) {
        require(!winningNumbers.contains(bonusNumber)) {
            "[ERROR] 당첨 번호와 보너스 번호는 중복될 수 없습니다."
        }
    }

    private fun isHaveBonusNumber(bonusNumber: LottoNumber): Boolean = lotto.numbers.contains(bonusNumber)
}

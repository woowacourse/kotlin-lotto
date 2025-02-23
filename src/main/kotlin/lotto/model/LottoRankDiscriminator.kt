package lotto.model

class LottoRankDiscriminator(
    private val winningLotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        validateWinningNumberAndBonusNumberDuplicate()
    }

    private fun validateWinningNumberAndBonusNumberDuplicate() {
        require(!isHaveBonusNumber(winningLotto)) {
            "[ERROR] 우승 번호와 보너스 번호는 중복될 수 없습니다."
        }
    }

    fun discriminateLotto(userLotto: Lotto): Rank {
        val countOfMatch = countMatchWinningNumbers(userLotto)
        val matchBonus = isHaveBonusNumber(userLotto)

        return Rank.fromMatchResult(countOfMatch, matchBonus)
    }

    private fun countMatchWinningNumbers(userLotto: Lotto): Int {
        val winningNumbers = winningLotto.numbers.map { it.number }
        return userLotto.numbers.count { it.number in winningNumbers }
    }

    private fun isHaveBonusNumber(lotto: Lotto): Boolean = bonusNumber.number in lotto.numbers.map { it.number }
}

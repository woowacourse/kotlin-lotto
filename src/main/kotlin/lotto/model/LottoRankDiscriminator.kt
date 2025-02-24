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

    fun countLottoByRank(lottos: List<Lotto>): Map<Rank, Int> {
        val countResult = Rank.entries.associateWith { 0 }.toMutableMap()

        lottos.forEach { lotto ->
            val rank = discriminateLotto(lotto)
            countResult[rank] = countResult.getValue(rank) + 1
        }

        return countResult
    }

    private fun discriminateLotto(userLotto: Lotto): Rank {
        val countOfMatch = countMatchWinningNumbers(userLotto)
        val matchBonus = isHaveBonusNumber(userLotto)

        return Rank.from(countOfMatch, matchBonus)
    }

    private fun countMatchWinningNumbers(userLotto: Lotto): Int {
        val winningNumbers = winningLotto.numbers.map { it.number }
        return userLotto.numbers.count { it.number in winningNumbers }
    }

    private fun isHaveBonusNumber(lotto: Lotto): Boolean = lotto.isHaveNumber(bonusNumber)
}

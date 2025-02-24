package lotto.model

class WinningLotto(
    val winningNumbers: Lotto,
    val bonusNumber: LottoNumber,
) {
    init {
        require(!winningNumbers.contains(bonusNumber)) { ERROR_DUPLICATED_BONUS_NUMBER }
    }

    fun calculateStatistics(
        lottos: Lottos,
        purchaseMoney: LottoPurchaseAmount,
    ): LottoStatistics {
        val rankStatistics: Map<Rank, Int> =
            lottos.lottoBundle
                .groupingBy { lotto -> findLottoRank(lotto) }
                .eachCount()
        return LottoStatistics(rankStatistics, purchaseMoney)
    }

    private fun findLottoRank(lotto: Lotto): Rank {
        val matchCount = winningNumbers.matchedCount(lotto)
        val isMatchedBonus = lotto.contains(bonusNumber)
        return Rank.valueOf(matchCount, isMatchedBonus)
    }

    companion object {
        private const val ERROR_DUPLICATED_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}

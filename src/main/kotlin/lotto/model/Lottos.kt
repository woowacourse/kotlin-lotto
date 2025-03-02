package lotto.model

class Lottos(
    private val manualLottos: List<Lotto>,
    private val autoLotts: List<Lotto>,
) {
    val lottoBundle = manualLottos + autoLotts

    val size: Int = lottoBundle.size

    fun calculateLottoResult(winningLotto: WinningLotto): LottoResult {
        val ranks: Map<Rank, Int> =
            lottoBundle
                .groupingBy { lotto -> getRank(lotto, winningLotto) }
                .eachCount()

        return LottoResult(ranks)
    }

    private fun getRank(
        lotto: Lotto,
        winningLotto: WinningLotto,
    ): Rank {
        val matchCount = lotto.getMatchCount(winningLotto.winningNumbers)
        val isBonusMatched = lotto.containsNumber(winningLotto.bonusNumber)
        return Rank.findRank(matchCount, isBonusMatched)
    }
}

package lotto.model

class WinningResult(
    private val lottos: Lottos,
    private val amount: Int,
) {
    fun countLottoByRank(
        winningNumbers: Set<LottoNumber>,
        bonusNumber: LottoNumber,
    ): Map<Rank, Int> {
        val countResult = Rank.entries.associateWith { 0 }.toMutableMap()

        lottos.getTotalLottos().forEach { lotto ->
            val winningLotto = WinningLotto(lotto)
            val rank = winningLotto.getRank(winningNumbers, bonusNumber)
            countResult[rank] = countResult.getValue(rank) + 1
        }

        return countResult
    }

    fun getProfitRate(
        winningNumbers: Set<LottoNumber>,
        bonusNumber: LottoNumber,
    ): Float {
        val countLottoByRank = countLottoByRank(winningNumbers, bonusNumber)
        val totalProfit = countLottoByRank.entries.sumOf { it.key.winningMoney * it.value }
        return formatProfitRate(totalProfit)
    }

    private fun formatProfitRate(totalProfit: Int): Float = totalProfit / amount.toFloat()
}

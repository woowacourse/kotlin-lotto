package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.WinningLotto
import lotto.domain.value.PurchaseAmount
import lotto.enums.Rank

class LottoCalculator {
    fun calculate(
        winningLotto: WinningLotto,
        lottos: List<Lotto>,
    ): Map<Rank, Int> = lottos.groupingBy { winningLotto.getRank(it) }.eachCount()

    fun calculateEarningRate(
        lottoStats: Map<Rank, Int>,
        purchaseAmount: PurchaseAmount,
    ): Double {
        val winningAmount = calculateTotalWinningAmount(lottoStats)
        val rate = winningAmount.toDouble() / purchaseAmount.amount
        return rate
    }

    private fun calculateTotalWinningAmount(lottoStats: Map<Rank, Int>): Int =
        lottoStats.entries.sumOf { (rank, count) -> rank.winningMoney * count }
}

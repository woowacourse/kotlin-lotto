package domain

import domain.lotto.LottoBundle

class WinStatistics(winningNumbers: WinningNumbers, purchasedLottoBundle: LottoBundle, spentMoney: Money) {
    val rankCount: Map<Rank, Int>
    val earningRate: Double = calculateEarningRate(spentMoney)

    init {
        rankCount = purchasedLottoBundle
            .compareWithWinningNumbers(winningNumbers)
            .groupingBy { it }
            .eachCount()
    }

    private fun getTotalIncome(): Money {
        return Money(
            rankCount
                .map { it.key.winningMoney * it.value.toLong() }
                .sum(),
        )
    }

    private fun calculateEarningRate(spentMoney: Money): Double {
        return getTotalIncome() / spentMoney
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WinStatistics

        if (rankCount != other.rankCount) return false

        return true
    }

    override fun hashCode(): Int {
        return rankCount.hashCode()
    }
}

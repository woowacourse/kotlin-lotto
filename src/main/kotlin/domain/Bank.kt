package domain

object Bank {
    fun getWinStatistics(winningResult: WinningResult): WinStatistics {
        val winStatistics = WinStatistics(
            mutableMapOf<Rank, Int>(
                Rank.FIRST to 0,
                Rank.SECOND to 0,
                Rank.THIRD to 0,
                Rank.FOURTH to 0,
                Rank.FIFTH to 0
        )
        )

        winningResult.comparingResults.forEach {
            val rank = Rank.valueOf(it.matchedCount, it.isBonusMatched)
            winStatistics.plusValue(rank,1)
        }

        return winStatistics
    }

    fun getEarningRate(totalPrize: Money, spendMoney: Money): Double {
        return totalPrize.amount / spendMoney.amount.toDouble()
    }


}
package domain

object Bank {
    fun getWinStatistics(winningResult: WinningResult): WinStatistics {
        val winStatistics = initWinStatistics()
        setWinStatistics(winningResult, winStatistics)

        return winStatistics
    }

    private fun setWinStatistics(winningResult: WinningResult, winStatistics: WinStatistics){
        winningResult.comparingResults.forEach {
            val rank = Rank.valueOf(it.matchedCount, it.isBonusMatched)
            winStatistics.plusValue(rank,1)
        }
    }

    private fun initWinStatistics() = WinStatistics(
        mutableMapOf<Rank, Int>(
            Rank.FIRST to 0,
            Rank.SECOND to 0,
            Rank.THIRD to 0,
            Rank.FOURTH to 0,
            Rank.FIFTH to 0
        )
    )

    fun getEarningRate(totalPrize: Money, spendMoney: Money): Double {
        return totalPrize.amount / spendMoney.amount.toDouble()
    }


}
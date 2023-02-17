package domain

object Bank {
    fun getWinStatistics(winningResultDto: WinningResultDto): WinStatistics {
        val winStatistics = initWinStatistics()
        setWinStatistics(winningResultDto, winStatistics)

        return winStatistics
    }

    private fun setWinStatistics(winningResultDto: WinningResultDto, winStatistics: WinStatistics) {
        winningResultDto.comparingResultDto.forEach {
            val rank = Rank.valueOf(it.matchedCount, it.isBonusMatched)
            winStatistics.plusValue(rank, 1)
        }
    }

    private fun initWinStatistics() = WinStatistics(
        mutableMapOf<Rank, Int>(
            Rank.FIRST to 0,
            Rank.SECOND to 0,
            Rank.THIRD to 0,
            Rank.FOURTH to 0,
            Rank.FIFTH to 0,
        ),
    )

    fun getEarningRate(totalPrize: Money, spendMoney: Money): Double {
        return totalPrize / spendMoney
    }
}

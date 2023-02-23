package lotto.domain

object RankCounter {
    fun count(lotteries: List<Lottery>, winningLottery: WinningLottery): Map<Rank, Int> {
        val ranks = lotteries.map {
            Rank.valueOf(
                it.countMatches(winningLottery.lottery),
                it.containBonusNumber(winningLottery.bonusNumber)
            )
        }
        return Rank.values().associateWith { key -> ranks.count { key == it } }
    }

    fun calculateTotalPrize(ranks: Map<Rank, Int>): Long {
        val prize = ranks.map { (rank, count) ->
            rank.calculatePrize(count)
        }.sum()
        return prize
    }
}

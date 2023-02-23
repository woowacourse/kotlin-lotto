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
}

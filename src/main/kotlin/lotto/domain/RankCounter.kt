package lotto.domain

object RankCounter {
    fun count(tickets: List<Lottery>, winningLottery: WinningLottery): Map<Rank, Int> {
        val ranks = tickets.map {
            Rank.valueOf(
                it.countMatches(winningLottery.lottery),
                it.containBonusNumber(winningLottery.bonusNumber)
            )
        }
        return Rank.values().associateWith { key -> ranks.count { key == it } }
    }
}

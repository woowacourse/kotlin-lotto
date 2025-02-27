package lotto.domain

data class WinningLottoTicket(
    val lotto: Lotto,
    val bonusNumber: LottoNumber,
) {
    fun getCountOfMatchWith(contrast: Lotto): Int = lotto.value.count { it in contrast.value }

    fun isMatchedBonusWith(contrast: Lotto): Boolean = lotto.contains(bonusNumber) && !contrast.contains(bonusNumber)

    fun findLottoRank(lotto: Lotto): Rank {
        val countOfMatch = this.getCountOfMatchWith(lotto)
        val isBonusMatched = this.isMatchedBonusWith(lotto)
        return Rank.getRank(countOfMatch, isBonusMatched)
    }

    fun findLottoRanks(manyLotto: List<Lotto>): ScoreRankMap {
        val rankMap = Rank.entries.associateWith { 0 }.toMutableMap()
        for (lotto in manyLotto) {
            val rank = findLottoRank(lotto)
            rankMap[rank] = rankMap.getOrDefault(rank, 0) + 1
        }
        return ScoreRankMap(rankMap.toMap())
    }
}

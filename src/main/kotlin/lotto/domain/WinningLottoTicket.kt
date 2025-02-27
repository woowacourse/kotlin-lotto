package lotto.domain

data class WinningLottoTicket(
    val lotto: Lotto,
    val bonusNumber: LottoNumber,
) {
    fun isMatchedBonusWith(contrast: Lotto): Boolean = lotto.contains(bonusNumber) && !contrast.contains(bonusNumber)

    fun findLottoRank(contrast: Lotto): Rank {
        val countOfMatch = lotto.getCountOfMatchWith(contrast)
        val isBonusMatched = this.isMatchedBonusWith(contrast)
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

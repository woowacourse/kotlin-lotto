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

    fun findLottoRanks(manyLotto: List<Lotto>): RankScoreBoard {
        val rankMap =
            manyLotto
                .map { findLottoRank(it) }
                .groupingBy { it }
                .eachCount()
        return RankScoreBoard.fromNecessaryKey(rankMap)
    }
}

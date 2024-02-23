package lotto.model

object LottoAnalyzer {
    fun calculateResult(
        lottoBundle: LottoBundle,
        drawResult: DrawResult,
    ): LottoResult {
        return LottoResult(
            lottoBundle.lottos.map { lotto ->
                val countOfMatch = lotto.getCountOfMatch(drawResult.winningLotto)
                val matchBonus = drawResult.getMatchBonusNumber(lotto)
                val rankState = RankState.valueOf(countOfMatch, matchBonus)
                Rank.valueOf(countOfMatch, rankState)
            }.groupingBy { it }.eachCount(),
        )
    }
}

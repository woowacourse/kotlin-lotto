package lotto.model

object LottoAnalyzer {
    fun calculateResult(matchResultResponse: MatchResultResponse): LottoResult {
        return LottoResult(
            matchResultResponse.lottoBundle.lottos.map { lotto ->
                val countOfMatch = lotto.getCountOfMatch(matchResultResponse.drawResult.winningLotto)
                val matchBonus = matchResultResponse.drawResult.getMatchBonusNumber(lotto)
                val rankState = RankState.valueOf(countOfMatch, matchBonus)
                Rank.valueOf(countOfMatch, rankState)
            }.groupingBy { it }.eachCount(),
        )
    }
}

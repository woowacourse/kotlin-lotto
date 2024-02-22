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
                Rank.valueOf(countOfMatch, matchBonus)
            }.groupingBy { it }.eachCount(),
        )
    }
}

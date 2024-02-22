package lotto.model

object LottoAnalyzer {
    fun calculateResult(
        lottoBundle: LottoBundle,
        drawResult: DrawResult,
    ): LottoResult {
        return LottoResult(
            lottoBundle.lottos.map { lotto ->
                val countOfMatch = lotto.lottoNumbers.intersect(drawResult.winningLotto.lottoNumbers.toSet()).size
                val matchBonus = drawResult.bonusNumber in lotto.lottoNumbers
                Rank.valueOf(countOfMatch, matchBonus)
            }.groupingBy { it }.eachCount(),
        )
    }
}

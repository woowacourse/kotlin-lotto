package lotto.model

object LottoAnalyzer {
    fun calculateResult(
        lottos: List<Lotto>,
        drawResult: DrawResult,
    ): LottoResult {
        return LottoResult(
            lottos.map { lotto ->
                val countOfMatch = lotto.calculateCountOfMatch(drawResult.winningLotto)
                val matchBonus = lotto.calculateMatchBonus(drawResult.bonusLottoNumber)
                Rank.valueOf(countOfMatch, matchBonus)
            }.groupingBy { it }.eachCount(),
        )
    }
}

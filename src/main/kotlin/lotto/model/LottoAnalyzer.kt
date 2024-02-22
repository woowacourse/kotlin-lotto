package lotto.model

object LottoAnalyzer {
    fun calculateResult(
        lottos: List<Lotto>,
        winningBundle: WinningBundle,
    ): LottoResult {
        return LottoResult(
            lottos.map { lotto ->
                val countOfMatch = lotto.calculateCountOfMatch(winningBundle.winningLotto)
                val matchBonus = lotto.calculateMatchBonus(winningBundle.bonusLottoNumber)
                Rank.valueOf(countOfMatch, matchBonus)
            }.groupingBy { it }.eachCount(),
        )
    }
}

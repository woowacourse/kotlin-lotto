package lotto.model

object LottoAnalyzer {
    fun calculateResult(
        lottos: List<Lotto>,
        drawResult: DrawResult,
    ): LottoResult {
        return LottoResult(
            lottos.map { lotto ->
                val countOfMatch = lotto.lottoNumbers.intersect(drawResult.winningLotto.lottoNumbers.toSet()).size
                val matchBonus = drawResult.bonusLottoNumber in lotto.lottoNumbers
                Rank.valueOf(countOfMatch, matchBonus)
            }.groupingBy { it }.eachCount(),
        )
    }
}

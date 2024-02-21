package lotto.model

class LottoAnalyzer(private val lottos: List<Lotto>, private val drawResult: DrawResult) {
    fun calculateResult(): LottoResult {
        return LottoResult(
            lottos.map { lotto ->
                val countOfMatch = lotto.lottoNumbers.intersect(drawResult.winningLotto.lottoNumbers.toSet()).size
                val matchBonus = drawResult.bonusNumber in lotto.lottoNumbers
                Rank.valueOf(countOfMatch, matchBonus)
            }.groupingBy { it }.eachCount(),
        )
    }
}

package lotto.model

class LottoAnalyzer(private val lottos: List<Lotto>, private val drawResult: DrawResult) {
    fun calculateResult(): LottoResult {
        return LottoResult(
            lottos.map { lotto ->
                val countOfMatch = lotto.numbers.intersect(drawResult.winningLotto.numbers.toSet()).size
                val matchBonus = drawResult.bonusNumber in lotto.numbers
                Rank.valueOf(countOfMatch, matchBonus)
            }.groupingBy { it }.eachCount(),
        )
    }

    companion object {
        const val LOTTO_SIZE = 6
        val LOTTO_NUMBER_RANGE: IntRange = 1..45

        const val BONUS_NUMBER_RANGE_ERROR_MESSAGE = "보너스 숫자는 1부터 45까지의 숫자로 구성되어야 합니다."
        const val BONUS_NUMBER_DUPLICATED_ERROR_MESSAGE = "보너스 숫자는 당첨 번호와 중복되면 안됩니다."
    }
}

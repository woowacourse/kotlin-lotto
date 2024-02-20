package lotto.model

class LottoGame(lottos: List<Lotto> = emptyList(), val winningLotto: Lotto, val bonusNumber: String) {
    var lottos: List<Lotto> = lottos
        private set

    init {
        require(bonusNumber.toIntOrNull() in LOTTO_NUMBER_RANGE) { BONUS_NUMBER_RANGE_ERROR_MESSAGE }
        require(bonusNumber !in winningLotto.numbers) { BONUS_NUMBER_DUPLICATED_ERROR_MESSAGE }
    }

    fun createLottos(count: Int) {
        lottos =
            List(count) {
                Lotto((LOTTO_NUMBER_RANGE).shuffled().take(LOTTO_SIZE).sorted().map { it.toString() })
            }
    }

    fun calculateResult(): Map<Rank, Int> {
        return lottos.map { lotto ->
            val countOfMatch = lotto.numbers.intersect(winningLotto.numbers.toSet()).size
            val matchBonus = bonusNumber in lotto.numbers
            Rank.valueOf(countOfMatch, matchBonus)
        }.groupingBy { it }.eachCount()
    }

    companion object {
        val LOTTO_SIZE = 6
        val LOTTO_NUMBER_RANGE: IntRange = 1..45

        const val BONUS_NUMBER_RANGE_ERROR_MESSAGE = "보너스 숫자는 1부터 45까지의 숫자로 구성되어야 합니다."
        const val BONUS_NUMBER_DUPLICATED_ERROR_MESSAGE = "보너스 숫자는 당첨 번호와 중복되면 안됩니다."
    }
}

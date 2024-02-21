package lotto.model

class LottoGame(lottos: List<Lotto> = emptyList(), val winningLotto: Lotto, val bonusNumber: String) {
    var lottos: List<Lotto> = lottos
        private set


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
    }
}

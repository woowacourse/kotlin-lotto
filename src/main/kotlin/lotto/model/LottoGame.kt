package lotto.model

class LottoGame(lottos: List<Lotto> = emptyList(), val winningLotto: Lotto, val bonusNumber: String) {
    var lottos: List<Lotto> = lottos
        private set

    init {
        require(bonusNumber.toIntOrNull() in 1..45)
        require(bonusNumber !in winningLotto.numbers)
    }

    fun createLottos(count: Int) {
        lottos =
            List(count) {
                Lotto((1..45).shuffled().take(6).sorted().map { it.toString() })
            }
    }

    fun calculateResult(): Map<Rank, Int> {
        return lottos.map { lotto ->
            val countOfMatch = lotto.numbers.intersect(winningLotto.numbers.toSet()).size
            val matchBonus = bonusNumber in lotto.numbers
            Rank.valueOf(countOfMatch, matchBonus)
        }.groupingBy { it }.eachCount()
    }
}

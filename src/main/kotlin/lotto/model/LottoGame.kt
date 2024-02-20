package lotto.model

class LottoGame(val lottos: List<Lotto>, val winningLotto: Lotto, val bonusNumber: String) {
    init {
        require(bonusNumber.toIntOrNull() in 1..45)
        require(bonusNumber !in winningLotto.numbers)
    }

    fun calculateResult(): Map<Rank, Int> {
        return lottos.map { lotto ->
            val countOfMatch = lotto.numbers.intersect(winningLotto.numbers.toSet()).size
            val matchBonus = bonusNumber in lotto.numbers
            Rank.valueOf(countOfMatch, matchBonus)
        }.groupingBy { it }.eachCount()
    }
}

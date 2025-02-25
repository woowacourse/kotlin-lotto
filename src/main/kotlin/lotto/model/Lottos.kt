package lotto.model

class Lottos(
    private val lottos: List<Lotto>,
) {
    operator fun plus(other: Lottos): Lottos = Lottos(this.lottos + other.lottos)

    fun countLottoByRank(
        winningNumbers: Set<LottoNumber>,
        bonusNumber: LottoNumber,
    ): Map<Rank, Int> {
        val countResult = Rank.entries.associateWith { 0 }.toMutableMap()

        lottos.forEach { lotto ->
            val winningLotto = WinningLotto(lotto)
            val rank = winningLotto.getRank(winningNumbers, bonusNumber)
            countResult[rank] = countResult.getValue(rank) + 1
        }

        return countResult
    }

    fun getAllLottoNumbers(): List<Set<Int>> =
        lottos.map { lotto ->
            val numbers = lotto.numbers
            numbers.map { it.number }.toSet()
        }
}

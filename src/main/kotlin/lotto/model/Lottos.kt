package lotto.model

class Lottos(
    private val lottos: List<Lotto>,
) {
    fun countLottoByRank(
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): Map<Rank, Int> {
        val countResult = Rank.entries.associateWith { 0 }.toMutableMap()

        lottos.forEach { lotto ->
            val rank = lotto.getRank(winningNumbers, bonusNumber)
            countResult[rank] = countResult.getValue(rank) + 1
        }

        return countResult
    }

    fun getAllLottoNumbers(): List<List<Int>> = lottos.map { lotto -> lotto.numbers }
}

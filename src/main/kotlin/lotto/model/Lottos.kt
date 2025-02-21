package lotto.model

class Lottos(
    val value: List<Lotto>,
) {
    fun countLottoByRank(
        winningNumbers: Lotto,
        bonusNumber: LottoNumber,
    ): Map<Rank, Int> {
        val countResult = Rank.entries.associateWith { 0 }.toMutableMap()

        value.forEach { lotto ->
            val rank = lotto.getRank(winningNumbers, bonusNumber)
            countResult[rank] = countResult.getValue(rank) + 1
        }

        return countResult
    }
}

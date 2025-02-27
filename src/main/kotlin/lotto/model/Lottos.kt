package lotto.model

class Lottos(
    private val lottos: List<Lotto>,
) {
    fun countLottoByRank(
        winningNumbers: Set<Int>,
        bonusNumber: Int,
    ): Map<Rank, Int> {
        val countResult = Rank.entries.associateWith { 0 }.toMutableMap()

        lottos.forEach { lotto ->
            val winningLotto = WinningLotto(lotto)
            val rank = winningLotto.getRank(winningNumbers, bonusNumber)
            countResult[rank] = countResult.getValue(rank) + 1
        }

        return countResult
    }

    fun getAllLottoNumbers(): List<Set<LottoNumber>> = lottos.map { lotto -> lotto.numbers }
}

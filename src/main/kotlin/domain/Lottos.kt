package domain

class Lottos(val lottos: List<Lotto>) : List<Lotto> by lottos {
    fun matchLottos(winningNumber: WinningNumber): WinningResult {
        val winningResult =
            mutableListOf(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH, Rank.MISS).associateWith { 0 }
                .toMutableMap()
        return WinningResult(matchEachLotto(winningNumber, winningResult).toMap())
    }

    fun matchEachLotto(winningNumber: WinningNumber, winningResult: MutableMap<Rank, Int>): MutableMap<Rank, Int> {
        lottos.forEach { lotto ->
            val rank = lotto.matchLotto(winningNumber.lotto, winningNumber.bonusNumber)
            if (rank != Rank.MISS) winningResult[rank!!] = winningResult.getOrDefault(rank, 0) + 1
        }
        return winningResult
    }
}

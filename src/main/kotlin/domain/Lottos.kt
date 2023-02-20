package domain

class Lottos(val lottos: List<Lotto>) {
    fun matchLottos(winningNumber: WinningNumber): WinningResult {
        val winningResult =
            mutableListOf(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH, Rank.MISS).associateWith { 0 }
                .toMutableMap()
        lottos.forEach { lotto ->
            val rank = lotto.matchLotto(winningNumber.lotto, winningNumber.bonusNumber)
            if (rank != Rank.MISS) winningResult[rank!!] = winningResult.getOrDefault(rank, 0) + 1
        }
        return WinningResult(winningResult.toMap())
    }
}

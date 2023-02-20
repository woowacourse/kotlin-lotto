package domain

class Lottos(val lottos: List<Lotto>) {
    fun matchLottos(winningNumber: WinningNumber): WinningResult {
        val winningResult = WinningResult()

        lottos.forEach { lotto ->
            var rank = lotto.matchLotto(winningNumber.lotto, winningNumber.bonusNumber)
            if (rank != null) winningResult.setWinnigResult(rank)
        }
        return winningResult
    }
}

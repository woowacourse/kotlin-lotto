package domain

class Lottos(val lottos: List<Lotto>) {
    fun matchLottos(winningLotto: Lotto, bonusNumber: BonusNumber): WinningResult {
        val winningResult = WinningResult()

        lottos.forEach { lotto ->
            var rank = lotto.matchLotto(winningLotto, bonusNumber)
            if (rank != null) winningResult.setWinnigResult(rank)
        }
        return winningResult
    }
}

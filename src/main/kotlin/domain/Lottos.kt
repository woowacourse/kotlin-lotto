package domain

class Lottos(val lottos: List<Lotto>) {
    fun matchLottos(winningLotto: Lotto, bonusNumber: BonusNumber): WinningResult {
        var winningResult = WinningResult()

        lottos.forEach { lotto ->
            var rank = lotto.matchLotto(winningLotto, bonusNumber)
            winningResult.setWinnigResult(rank)
        }
        return winningResult
    }
}

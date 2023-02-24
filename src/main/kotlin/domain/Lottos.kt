package domain

class Lottos(val lottos: List<Lotto>) : List<Lotto> by lottos {
    fun matchLottos(winningNumber: WinningNumber): WinningResult {
        val winningResult = Rank.values().associateWith {
            rank ->
            lottos.count { rank == it.matchLotto(winningNumber.lotto, winningNumber.bonusNumber) }
        }
        return WinningResult(winningResult)
    }
}

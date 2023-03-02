package lotto.domain

class LottoBunch(val value: List<Lotto>) {

    fun calculateRanks(winningLotto: WinningLotto): List<Rank> =
        value.map { winningLotto.calculateRank(it) }
}

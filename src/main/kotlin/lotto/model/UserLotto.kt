package lotto.model

class UserLotto(val lottos: List<Lotto>) {

    fun getWinningStatistics(winningLotto: WinningLotto): List<Int> {
        val winningStatistics = MutableList(Rank.values().size) { 0 }
        lottos.forEach {
            val index = it.getCountOfMatch(winningLotto).ordinal
            winningStatistics[index]++
        }
        return winningStatistics
    }
}

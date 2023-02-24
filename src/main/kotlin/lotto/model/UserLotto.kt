package lotto.model

class UserLotto(val lotto: List<Lotto>) {

    fun getWinningStatistics(winningLotto: WinningLotto): List<Int> {
        val winningStatistics = MutableList(Rank.values().size) { 0 }
        lotto.forEach {
            val index = it.getRank(winningLotto).ordinal
            winningStatistics[index]++
        }
        return winningStatistics
    }
}

package lotto

class Lotto(val lotto: List<Int>) {
    fun getCountOfMatch(winningLotto: WinningLotto): Rank {
        val count = lotto.count { winningLotto.winningNumbers.lotto.contains(it) }
        return Rank.values().find {
            it.countOfMatch == count
        } ?: Rank.MISS
    }
}

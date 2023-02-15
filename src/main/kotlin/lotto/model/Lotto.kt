package lotto.model

class Lotto(val lotto: List<Int>) {
    fun getCountOfMatch(winningLotto: WinningLotto): Rank {
        val count = lotto.count { winningLotto.winningNumbers.lotto.contains(it) }
        if (count == Rank.SECOND.countOfMatch) {
            return getRankByBonusNumber(winningLotto)
        }
        return Rank.values().find {
            it.countOfMatch == count
        } ?: Rank.MISS
    }

    private fun getRankByBonusNumber(winningLotto: WinningLotto) =
        if (lotto.contains(winningLotto.bonusNumber)) Rank.SECOND else Rank.THIRD
}

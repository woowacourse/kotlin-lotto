package model

class WinningLotto(private val winningLotto: Lotto, private val bonusNumber: Int) {
    fun calculateCountOfMatch(lotto: Lotto): Int {
        return winningLotto.numbers.intersect(lotto.numbers.toSet()).size
    }

    fun checkBonusNumberMatched(lotto: Lotto): Boolean {
        return lotto.numbers.contains(bonusNumber)
    }
}

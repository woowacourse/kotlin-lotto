package model

class WinningLotto(private val lotto: Lotto, private val bonusNumber: Int) {
    fun calculateCountOfMatch(lotto: Lotto): Int {
        return this.lotto.getNumbers().intersect(lotto.getNumbers().toSet()).size
    }

    fun checkBonusNumberMatched(lotto: Lotto): Boolean {
        return lotto.getNumbers().contains(bonusNumber)
    }
}

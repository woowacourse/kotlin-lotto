package model

open class WinningLotto(private val winningNumbers: List<LottoNumber>, private val bonusNumber: Int) :
    Lotto(winningNumbers) {
    fun calculateCountOfMatch(lotto: Lotto): Int {
        return winningNumbers.toSet().intersect(lotto.numbers).size
    }

    fun checkBonusNumberMatched(lotto: Lotto): Boolean {
        return lotto.numbers.any { it.number == bonusNumber }
    }
}

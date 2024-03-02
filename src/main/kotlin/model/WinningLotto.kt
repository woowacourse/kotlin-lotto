package model

class WinningLotto(private val winningNumbers: List<LottoNumber>, private val bonusNumber: LottoNumber) {
    init {
        require(winningNumbers.toSet().size == 6)
        require(!winningNumbers.contains(bonusNumber))
    }

    fun calculateCountOfMatch(lotto: Lotto): Int {
        return winningNumbers.toSet().intersect(lotto.numbers).size
    }

    fun checkBonusNumberMatched(lotto: Lotto): Boolean {
        return lotto.numbers.any { it == bonusNumber }
    }
}

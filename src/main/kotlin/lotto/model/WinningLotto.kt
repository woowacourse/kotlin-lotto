package lotto.model

class WinningLotto(private val lotto: Lotto, private val bonusNumber: LottoNumber) {
    fun calculateCountOfMatch(lotto: Lotto): Int {
        val winningNumbers = this.lotto.getNumbers().map { it.getNumber() }
        val lottoNumbers = lotto.getNumbers().map { it.getNumber() }

        return winningNumbers.intersect(lottoNumbers.toSet()).size
    }

    fun checkBonusNumberMatched(lotto: Lotto): Boolean {
        val lottoNumbers = lotto.getNumbers().map { it.getNumber() }

        return lottoNumbers.contains(bonusNumber.getNumber())
    }
}

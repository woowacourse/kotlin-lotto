package model

class WinningLotto(
    private val winningNumbers: Lotto,
    bonusNumber: LottoNumber,
) {
    init {
        require(!winningNumbers.lottoNumbers.map { it.number }.contains(bonusNumber.number))
    }

    fun getCountOfMatch(lotto: Lotto): Int {
        val winningNumbers = winningNumbers.lottoNumbers.map { it.number }.toSet()
        val lottoNumbers = lotto.lottoNumbers.map { it.number }.toSet()
        return winningNumbers.intersect(lottoNumbers).size
    }
}

package domain.model

import domain.value.LottoNumber
import enums.Rank

class WinningLotto(
    private val winningNumbers: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!winningNumbers.lottoNumbers.map { it.number }.contains(bonusNumber.number))
    }

    fun getRank(lotto: Lotto): Rank {
        val countOfMatch = getCountOfMatch(lotto)
        val matchBonus = getMatchBonus(lotto)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun getCountOfMatch(lotto: Lotto): Int {
        val winningNumbers = winningNumbers.lottoNumbers.map { it.number }.toSet()
        val lottoNumbers = lotto.lottoNumbers.map { it.number }.toSet()
        return winningNumbers.intersect(lottoNumbers).size
    }

    private fun getMatchBonus(lotto: Lotto): Boolean = lotto.lottoNumbers.map { it.number }.contains(bonusNumber.number)
}

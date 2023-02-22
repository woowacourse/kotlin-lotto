package domain

import model.BonusNumber
import model.Lotto

class LottoAdministrator {
    fun getMatchOfNumber(lottoNumber: Lotto, winningNumber: Lotto): Int {
        val lottoNumbers = lottoNumber.getNumbers()
        return lottoNumbers.filter { winningNumber.getNumbers().contains(it) }.size
    }

    fun isMatchBonus(lottoNumber: Lotto, bonusNumber: BonusNumber): Boolean {
        val lottoNumbers = lottoNumber.getNumbers()
        return lottoNumbers.contains(bonusNumber.bonusNumber.number)
    }

    fun getRank(countOfMatch: Int, matchBonus: Boolean) = Rank.valueOf(countOfMatch, matchBonus)
}

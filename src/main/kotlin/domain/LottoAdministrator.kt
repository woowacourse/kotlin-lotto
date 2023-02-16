package domain

import model.BonusNumber
import model.Lotto

class LottoAdministrator {
    fun getMatchOfNumber(lottoNumber: Lotto, winningNumber: Lotto): Int {
        return lottoNumber.lottoNumbers.filter { winningNumber.lottoNumbers.contains(it) }.size
    }

    fun isMatchBonus(lottoNumber: Lotto, bonusNumber: BonusNumber): Boolean {
        return lottoNumber.lottoNumbers.contains(bonusNumber.bonusNumber)
    }

    fun getRank(countOfMatch: Int, matchBonus: Boolean): Rank? {
        return Rank.valueOf(countOfMatch, matchBonus)
    }
}

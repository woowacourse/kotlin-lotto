package domain

import model.BonusNumber
import model.Lotto
import model.LottoNumber

class LottoAdministrator {
    fun getMatchOfNumber(lottoNumber: Lotto, winningNumber: Lotto): Int {
        val lottoNumbers: List<LottoNumber> = lottoNumber.getNumbers()
            .map { number ->
                LottoNumber(number)
            }

        return Lotto(lottoNumbers).getMatchOfNumber(winningNumber)
    }

    fun isMatchBonus(lottoNumber: Lotto, bonusNumber: BonusNumber): Boolean {
        val lottoNumbers = lottoNumber.getNumbers()

        return lottoNumbers.contains(bonusNumber.bonusNumber.number)
    }

    fun getRank(countOfMatch: Int, matchBonus: Boolean) = Rank.valueOf(countOfMatch, matchBonus)
}

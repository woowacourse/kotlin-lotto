package domain

import model.WinningLotto
import model.Lotto

class LottoAdministrator {
    fun getMatchOfNumber(lottoNumber: Lotto, winningNumber: Lotto): Int {
        val lottoNumbers = lottoNumber.getNumbers()
        return lottoNumbers.filter { winningNumber.getNumbers().contains(it) }.size
    }

    fun isMatchBonus(lottoNumber: Lotto, winningLotto: WinningLotto): Boolean {
        val lottoNumbers = lottoNumber.getNumbers()
        return lottoNumbers.contains(winningLotto.getBonusNumber())
    }

    fun getRank(countOfMatch: Int, matchBonus: Boolean): Rank? {
        return Rank.valueOf(countOfMatch, matchBonus)
    }
}

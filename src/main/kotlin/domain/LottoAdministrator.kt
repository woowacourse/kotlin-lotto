package domain

class LottoAdministrator {
    fun compareWinningNumber(lottoNumber: List<Int>, winningNumber: List<Int>): Int {
        return lottoNumber.filter { winningNumber.contains(it) }.size
    }

    fun compareBonusNumber(lottoNumber: List<Int>, bonusNumber: Int): Boolean {
        return lottoNumber.contains(bonusNumber)
    }

    fun getRank(countOfMatch: Int, matchBonus: Boolean): Rank? {
        return Rank.valueOf(countOfMatch, matchBonus)
    }

}

package lotto.model

class Lotto(private val lottoNumber: LottoNumber) {

    fun getLottoNumber(): Set<Int> {
        return lottoNumber.getNumbers()
    }

    fun matchCount(winningNumber: WinningNumber): Int {
        return winningNumber
            .getWinning()
            .getLottoNumber()
            .intersect(getLottoNumber())
            .size
    }

    fun matchBonusNumber(winningNumber: WinningNumber): Boolean {
        return lottoNumber.getNumbers().contains(winningNumber.getBonusNumber())
    }

    fun findRanking(winningNumber: WinningNumber): LottoPrize {
        var rank = LottoPrize.entries.find {
            it.getMatchNumbers() == matchCount(winningNumber)
        } ?: LottoPrize.BOOM
        if (checkSecond(rank, matchBonusNumber(winningNumber))) rank = LottoPrize.SECOND
        return rank
    }

    private fun checkSecond(rank: LottoPrize, matchBonus: Boolean) = rank == LottoPrize.THIRD && matchBonus

    companion object {
        const val LOTTO_LEN = 6
        const val LOTTO_PRICE = 1000.0
        val LOTTO_NUM_RANGE = (1..45)
    }

    fun findRanking(winningNumber: WinningNumber): LottoPrize {
        var rank = LottoPrize.entries.find {
            it.getMatchNumbers() == matchCount(winningNumber)
        } ?: LottoPrize.BOOM
        if (checkSecond(rank, matchBonusNumber(winningNumber))) rank = LottoPrize.SECOND
        return rank
    }

    private fun checkSecond(rank: LottoPrize, matchBonus: Boolean) = rank == LottoPrize.THIRD && matchBonus
}

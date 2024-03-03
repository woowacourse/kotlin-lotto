package lotto.model

import lotto.model.user.UserException
import lotto.model.winning.WinningNumber

class Lotto(private val lottoNumbers: LottoNumbers) {

    fun getLottoNumber(): Set<Int> {
        return lottoNumbers.getNumbers()
    }

    fun matchCount(winningNumber: WinningNumber): Int {
        return winningNumber
            .getWinning()
            .getLottoNumber()
            .intersect(getLottoNumber())
            .size
    }

    fun matchBonusNumber(winningNumber: WinningNumber): Boolean {
        return lottoNumbers.getNumbers().contains(winningNumber.getBonusNumber())
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

        fun checkLottoValid(numbers: List<String>): LottoEvent {
            return runCatching {
                val lottoNumbers = LottoNumbers.checkNumbersValid(numbers)
                val lotto = Lotto(lottoNumbers = LottoNumbers(lottoNumbers))
                LottoEvent.Success(lotto = lotto)
            }.getOrElse { exception ->
                when (exception) {
                    is UserException.LottoException -> exception.lottoEvent
                    else -> LottoEvent.UnknownError
                }
            }
        }

        const val LOTTO_LEN = 6
        const val LOTTO_PRICE = 1000.0
        val LOTTO_NUM_RANGE = (1..45)
    }
}

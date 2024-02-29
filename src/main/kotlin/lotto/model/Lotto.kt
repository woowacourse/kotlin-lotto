package lotto.model

import lotto.util.Constant

class Lotto(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == Constant.LOTTO_LEN) {
            "로또는 6개의 숫자로 이루어져있습니다."
        }
    }

    fun findRanking(winningNumber: WinningNumber): LottoPrize {
        val matchCount = winningNumber.matchCount(this)
        val matchBonus = winningNumber.matchBonusNumber(this)
        return LottoPrize.findRanking(matchCount, matchBonus)
    }

    fun contains(number: LottoNumber): Boolean {
        return number in numbers
    }
}

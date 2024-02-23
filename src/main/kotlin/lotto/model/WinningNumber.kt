package lotto.model

import lotto.util.LottoRule

class WinningNumber(
    private val lotto: Lotto,
    private val bonusNumber: Int,
) {
    init {
        require(bonusNumber in LottoRule.LOTTO_NUM_RANGE)
    }

    fun getWinning(): Lotto {
        return lotto
    }

    fun getBonusNumber(): Int {
        return bonusNumber
    }
}

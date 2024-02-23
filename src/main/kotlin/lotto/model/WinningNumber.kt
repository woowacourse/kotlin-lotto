package lotto.model

import lotto.util.Constant

class WinningNumber(
    private val lotto: Lotto,
    private val bonusNumber: Int,
) {
    init {
        require(bonusNumber in Constant.LOTTO_NUM_RANGE)
    }

    fun getWinning(): Lotto {
        return lotto
    }

    fun getBonusNumber(): Int {
        return bonusNumber
    }
}
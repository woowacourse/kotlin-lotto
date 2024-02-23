package lotto.model

import lotto.constants.LottoPrize
import lotto.constants.StringConstants.INVALID_DUPLICATE_BONUS_NUMBER

class WinningLotto(
    private val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!lotto.contains(bonusNumber)) { INVALID_DUPLICATE_BONUS_NUMBER }
    }

    fun getLottoPrize(otherLotto: Lotto): LottoPrize {
        val matchingCount = otherLotto.getMatchingCount(lotto)
        val isMatchingBonus = otherLotto.contains(bonusNumber)
        return LottoPrize.valueOf(matchingCount, isMatchingBonus)
    }
}

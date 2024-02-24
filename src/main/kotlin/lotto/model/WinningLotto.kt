package lotto.model

import lotto.constants.LottoPrize

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

    companion object {
        private const val INVALID_DUPLICATE_BONUS_NUMBER = "당첨 번호와 중복되지 않는 보너스 번호를 입력해 주세요."
    }
}

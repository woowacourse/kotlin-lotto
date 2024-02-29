package lotto.model

import lotto.exception.ErrorCode.BONUS_NUMBER_DUPLICATE
import lotto.exception.ExceptionsHandler.handleValidation

data class DrawResult(
    val winningLotto: Lotto,
    val bonusNumber: LottoNumber,
) {
    init {
        handleValidation(BONUS_NUMBER_DUPLICATE) { bonusNumber !in winningLotto.lottoNumbers }
    }

    fun getMatchBonusNumber(lotto: Lotto) = bonusNumber in lotto.lottoNumbers
}

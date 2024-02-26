package lotto.model

import lotto.exception.ErrorCode.MANUAL_PURCHASE_COUNT_NOT_NATURAL_NUMBER
import lotto.exception.ExceptionsHandler

data class LottoManualPurchaseCount(val count: Int) {
    companion object {
        fun from(strCount: String): LottoManualPurchaseCount {
            ExceptionsHandler.handleValidation(MANUAL_PURCHASE_COUNT_NOT_NATURAL_NUMBER) { strCount.toIntOrNull() != null }
            return LottoManualPurchaseCount(strCount.toInt())
        }
    }
}

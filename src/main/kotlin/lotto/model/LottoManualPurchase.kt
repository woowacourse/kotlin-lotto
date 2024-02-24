package lotto.model

import lotto.exception.ErrorCode.MANUAL_PURCHASE_COUNT_NOT_NATURAL_NUMBER
import lotto.exception.ExceptionsHandler

data class LottoManualPurchase(val count: Int) {
    companion object {
        fun from(strCount: String): LottoManualPurchase {
            ExceptionsHandler.handleValidation(MANUAL_PURCHASE_COUNT_NOT_NATURAL_NUMBER) { strCount.toIntOrNull() != null }
            return LottoManualPurchase(strCount.toInt())
        }
    }
}

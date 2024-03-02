package model

import exception.ErrorCode
import exception.ExceptionHandler.handleException

data class ManualLottoPurchaseCount(val count: Int) {
    companion object {
        fun from(
            count: String,
            numberOfLotto: Int,
        ): ManualLottoPurchaseCount {
            handleException(ErrorCode.INVALID_PURCHASE_SIZE_OF_MANUAL_LOTTO) { count.toIntOrNull() != null }
            handleException(ErrorCode.INVALID_PURCHASE_SIZE_OF_MANUAL_LOTTO) { count.toInt() >= 0 }
            handleException(ErrorCode.INVALID_PURCHASE_SIZE_OF_MANUAL_LOTTO_AMOUNT) { count.toInt() <= numberOfLotto }

            return ManualLottoPurchaseCount(count.toInt())
        }
    }
}

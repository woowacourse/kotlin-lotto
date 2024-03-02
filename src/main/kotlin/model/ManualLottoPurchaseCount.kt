package model

import util.Validator

data class ManualLottoPurchaseCount(val count: Int) {
    companion object {
        fun from(
            count: String,
            purchaseAmount: Int,
        ): ManualLottoPurchaseCount {
            Validator.validatePurchaseSizeOfManualLotto(count, purchaseAmount)
            return ManualLottoPurchaseCount(count.toInt())
        }
    }
}

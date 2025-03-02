package lotto.controller

import lotto.domain.LottoAmount

class Validator {
    companion object {
        fun manualAmountValidator(
            manualAmount: LottoAmount,
            totalAmount: LottoAmount,
        ) {
            require(!manualAmount.isGreaterThan(totalAmount)) {
                MANUAL_PURCHASE_AMOUNT_ERROR_MESSAGE
            }
        }

        private const val MANUAL_PURCHASE_AMOUNT_ERROR_MESSAGE = "수동으로 구매할 로또의 수는 구입할 로또의 수보다 클 수 없습니다"
    }
}

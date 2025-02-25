package lotto.controller

class Validator {
    companion object {
        fun manualAmountValidator(
            manualAmount: Int,
            amount: Int,
        ) {
            require(manualAmount >= 0) { MANUAL_LOTTO_AMOUNT_ERROR_MESSAGE }
            require(manualAmount <= amount) { MANUAL_PURCHASE_AMOUNT_ERROR_MESSAGE }
        }

        private const val MANUAL_LOTTO_AMOUNT_ERROR_MESSAGE = "수동으로 구매할 로또의 수는 정수로 입력해야 한다"
        private const val MANUAL_PURCHASE_AMOUNT_ERROR_MESSAGE = "수동으로 구매할 로또의 수는 구입할 로또의 수보다 클 수 없습니다"
    }
}

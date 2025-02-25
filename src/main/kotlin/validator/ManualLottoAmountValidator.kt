package validator

class ManualLottoAmountValidator(val value: Int) {
    init {
        require(value >= 0) { ENTER_LESS_THAN_AMOUNT_PURCHASED }
    }

    companion object {
        private const val ENTER_LESS_THAN_AMOUNT_PURCHASED = "구매한 갯수보다 낮게 입력해주십시오."
    }
}

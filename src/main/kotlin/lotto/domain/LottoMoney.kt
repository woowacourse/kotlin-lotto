package lotto.domain

import lotto.controller.Controller

class LottoMoney(val amount: Int) {

    init {
        require(amount > 0) { MONEY_NEGATIVE_NUMBER_ERROR }
        require(amount % Controller.MONEY_UNIT == 0) { MONEY_UNIT_ERROR }
    }

    companion object {
        private const val MONEY_NEGATIVE_NUMBER_ERROR = "금액은 양수여야 합니다."
        private const val MONEY_UNIT_ERROR = "금액은 1000원 단위여야 합니다."
    }
}

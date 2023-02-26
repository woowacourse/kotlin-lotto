package lotto.domain.model

import lotto.view.OutputView

class LottoMoney(val amount: Int) {

    init {
        require(checkMoneyAvailable(amount)) { MONEY_MINIMUM_ERROR }
    }

    companion object {
        fun checkMoneyAvailable(amount: Int): Boolean {
            if (amount < MONEY_UNIT) {
                println(OutputView.ERROR_PREFIX + MONEY_MINIMUM_ERROR)
                return false
            }
            return true
        }

        const val MONEY_MINIMUM_ERROR = "최소 금액은 1000원입니다."
        const val MONEY_UNIT = 1000
    }
}

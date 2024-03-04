package lotto.model

import lotto.util.InputValidation

class PurchaseAmount(val money: Int, val numberOfManualLottos: Int) {
    init {
        InputValidation.validateAmountRange(money)
        require((money / PURCHASE_UNIT) > numberOfManualLottos) { ERROR_NOT_ENOUGH_MONEY_MESSAGE }
    }

    constructor(money: String, numberOfManualLottos: String) : this(
        validateInputMoney(money),
        validateNumberOfManualLottos(numberOfManualLottos),
    )

    fun getNumberOfAutoLottos(): Int {
        return (money / PURCHASE_UNIT) - numberOfManualLottos
    }

    companion object {
        private const val ERROR_PREFIX = "[ERROR] "
        private const val PURCHASE_UNIT = 1000
        private const val ERROR_MANUAL_NUMBER_RANGE_MESSAGE = "${ERROR_PREFIX}0이상의 수를 입력해주세요"
        private const val ERROR_NOT_ENOUGH_MONEY_MESSAGE = "${ERROR_PREFIX}금액이 부족합니다."
        private const val MINIMUM_MANUAL_NUMBER_RANGE = 0

        private fun validateInputMoney(inputMoney: String): Int {
            InputValidation.validateIsDigit(inputMoney)
            InputValidation.validateAmountRange(inputMoney.toInt())
            return inputMoney.toInt()
        }

        private fun validateNumberOfManualLottos(numberOfManualLottos: String): Int {
            InputValidation.validateIsDigit(numberOfManualLottos)
            validateManualNumberRange(numberOfManualLottos.toInt())
            return numberOfManualLottos.toInt()
        }

        private fun validateManualNumberRange(numberOfManualLottos: Int) {
            require(numberOfManualLottos >= MINIMUM_MANUAL_NUMBER_RANGE) { ERROR_MANUAL_NUMBER_RANGE_MESSAGE }
        }
    }
}

package presentation

class InputMoneyView {

    fun getMoney(): Int? {
        printGuideMessage()

        return readLine()?.toIntOrNull()
    }

    private fun printGuideMessage() = println(ENTER_MONEY)

    fun printInvalidValueError() = println(ERROR_VALUE)

    fun printUnderConditionError() = println(ERROR_UNDER_MINIMUM_PRICE)

    fun printCheckPriceUnit() = println(ERROR_CHECK_PRICE_UNIT)

    companion object {
        private const val ERROR_VALUE = "[ERROR] 숫자를 필수로 입력해야합니다."
        private const val ERROR_UNDER_MINIMUM_PRICE = "[ERROR] 최소 천원이상 입력해야합니다"
        private const val ERROR_CHECK_PRICE_UNIT = "[ERROR] 천원 단위로 입력해야합니다"
        private const val ENTER_MONEY = "구입금액을 입력해 주세요."
    }
}

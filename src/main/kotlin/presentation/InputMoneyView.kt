package presentation

class InputMoneyView {

    fun getMoney(): Int? {
        printGuideMessage()

        return readLine()?.toIntOrNull()
    }

    private fun printGuideMessage() = println(ENTER_MONEY)

    fun printInvalidValueError() = println(ERROR_VALUE)

    companion object {
        private const val ERROR_VALUE = "[ERROR] 숫자를 필수로 입력해야합니다."
        private const val ENTER_MONEY = "구입금액을 입력해 주세요."
    }
}

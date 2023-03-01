package presentation

class InputManualLottoView {

    fun getManualLottoCount(): Int? {
        printGuideMessage()

        return readLine()?.toIntOrNull()
    }

    // fun getManualLotto() = List...

    fun printInvalidValueError() = println(ERROR_VALUE)

    private fun printGuideMessage() = println(ENTER_MANUAL_LOTTO_COUNT)

    companion object {
        private const val ENTER_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val ERROR_VALUE = "[ERROR] 숫자를 필수로 입력해야합니다."
    }
}

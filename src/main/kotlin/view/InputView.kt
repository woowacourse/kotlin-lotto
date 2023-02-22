package view

object InputView {

    private const val MANUAL_LOTTO_NUMBERS_REQUEST_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
    private const val MANUAL_LOTTO_COUNT_REQUEST_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val PURCHASE_MONEY_REQUEST_MESSAGE = "구입금액을 입력해 주세요."
    private const val CATCH_NUMBER_REQUEST_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
    private const val BONUS_NUMBER_REQUEST_MESSAGE = "보너스 볼을 입력해 주세요."
    private const val NUMERIC_ERROR = "[숫자가 아닌 입력은 허용하지 않습니다.]\n다시 입력해주세요."
    private const val TOKENIZER = ","

    private const val INITIAL_PURCHASING = 0

    private fun requestNumericInput(message: String): Int {
        println(message)

        while (true) {
            readln().toIntOrNull()?.let { number -> return number }
            println(NUMERIC_ERROR)
        }
    }

    private fun isNumericInputs(inputs: List<String>): List<Int>? {
        val numbers = inputs.mapNotNull(String::toIntOrNull)

        if (numbers.size != inputs.size) {
            return null
        }
        return numbers
    }

    private fun requestNumericInputs(): List<Int> {
        while (true) {
            isNumericInputs(readln().split(TOKENIZER))?.let { manualLottoNumbers -> return manualLottoNumbers }
            println(NUMERIC_ERROR)
        }
    }

    fun requestPurchaseMoney(): Int = requestNumericInput(PURCHASE_MONEY_REQUEST_MESSAGE)

    fun requestManualLottoCount(): Int = requestNumericInput(MANUAL_LOTTO_COUNT_REQUEST_MESSAGE)

    fun requestManualLottoNumbers(numberOfPurchased: Int = INITIAL_PURCHASING): List<Int> {
        if (numberOfPurchased == INITIAL_PURCHASING) {
            println(MANUAL_LOTTO_NUMBERS_REQUEST_MESSAGE)
        }

        return requestNumericInputs()
    }

    fun requestCatchNumbers(): List<Int> {
        println(CATCH_NUMBER_REQUEST_MESSAGE)

        return requestNumericInputs()
    }

    fun requestBonusNumber(): Int = requestNumericInput(BONUS_NUMBER_REQUEST_MESSAGE)
}

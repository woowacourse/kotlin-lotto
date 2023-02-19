package view

object InputView {

    private const val MANUAL_LOTTO_NUMBERS_REQUEST_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
    private const val MANUAL_LOTTO_COUNT_REQUEST_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val PURCHASE_MONEY_REQUEST_MESSAGE = "구입금액을 입력해 주세요."
    private const val CATCH_NUMBER_REQUEST_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
    private const val BONUS_NUMBER_REQUEST_MESSAGE = "보너스 볼을 입력해 주세요."
    private const val TOKENIZER = ","
    private const val INITIAL_PURCHASING = 0

    private fun requestNumbers(): List<String> = readln().split(TOKENIZER).map { it.trim() }

    fun requestPurchaseMoney(): String {
        println(PURCHASE_MONEY_REQUEST_MESSAGE)

        return readln()
    }

    fun requestManualLottoCount(): String {
        println(MANUAL_LOTTO_COUNT_REQUEST_MESSAGE)

        return readln()
    }

    fun requestManualLottoNumbers(numberOfPurchased: Int): List<String> {
        if(numberOfPurchased == INITIAL_PURCHASING){
            println(MANUAL_LOTTO_NUMBERS_REQUEST_MESSAGE)
        }

        return requestNumbers()
    }

    fun requestCatchNumbers(): List<String> {
        println(CATCH_NUMBER_REQUEST_MESSAGE)

        return requestNumbers()
    }

    fun requestBonusNumber(): String {
        println(BONUS_NUMBER_REQUEST_MESSAGE)

        return readln()
    }
}

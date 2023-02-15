object InputView {

    private const val PURCHASE_MONEY_REQUEST_MESSAGE = "구입금액을 입력해 주세요."
    private const val CATCH_NUMBER_REQUEST_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
    private const val BONUS_NUMBER_REQUEST_MESSAGE = "보너스 볼을 입력해 주세요."
    private const val TOKENIZER = ","
    fun requestPurchaseMoney(): String {
        println(PURCHASE_MONEY_REQUEST_MESSAGE)

        return readln()
    }

    fun requestCatchNumbers(): List<String> {
        println(CATCH_NUMBER_REQUEST_MESSAGE)

        return readln().split(TOKENIZER).map { it.trim() }
    }

    fun requestBonusNumber(): String {
        println(BONUS_NUMBER_REQUEST_MESSAGE)

        return readln()
    }
}

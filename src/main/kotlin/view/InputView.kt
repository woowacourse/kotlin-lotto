package view

object InputView {

    private const val PURCHASE_MONEY_REQUEST_MESSAGE = "구입금액을 입력해 주세요."
    private const val CATCH_NUMBER_REQUEST_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
    private const val BONUS_NUMBER_REQUEST_MESSAGE = "보너스 볼을 입력해 주세요."
    private const val NUMBER_OF_MANUAL_LOTTOS_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val TOKENIZER = ","

    fun requestPurchaseMoney(): String {
        println(PURCHASE_MONEY_REQUEST_MESSAGE)

        return readln()
    }

    fun requestNumberOfManualLottos(): String {
        println(NUMBER_OF_MANUAL_LOTTOS_MESSAGE)

        return readln()
    }

    fun requestManualLottoNumbers(): List<String> {
        return readln().split(TOKENIZER).map { it.trim() }
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

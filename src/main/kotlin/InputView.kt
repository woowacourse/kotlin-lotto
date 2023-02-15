object InputView {

    private const val PURCHASE_MONEY_REQUEST_MESSAGE = "구입금액을 입력해 주세요."

    fun requestPurchaseMoney(): Int? {
        println(PURCHASE_MONEY_REQUEST_MESSAGE)
        return readln().toIntOrNull()
    }

}

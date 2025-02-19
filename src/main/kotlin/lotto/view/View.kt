package lotto.view

object View {
    fun readPriceInput() {
        println(MESSAGE_ENTER_PRICE)
        val input: String = readLine() ?: throw IllegalArgumentException(MESSAGE_INVALID_INPUT_STATE)
        val price: Int = input.toIntOrNull() ?: throw IllegalArgumentException(MESSAGE_PRICE_NOT_A_NUMBER)
    }

    private const val MESSAGE_INVALID_INPUT_STATE = "정상적으로 입력되지 않았습니다."
    private const val MESSAGE_PRICE_NOT_A_NUMBER = "숫자를 입력해주세요."
    private const val MESSAGE_ENTER_PRICE = "구입금액을 입력해 주세요."
}

package view

class OutputView {
    fun outputMoneyMessage() {
        println(OUTPUT_MONEY_MESSAGE)
    }

    companion object {
        const val OUTPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요."
    }
}

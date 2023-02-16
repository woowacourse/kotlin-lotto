package view

class OutputView {
    fun outputMoneyMessage() {
        println(OUTPUT_MONEY_MESSAGE)
    }

    fun outputLottoSizeMessage(size: Int) {
        println(size.toString() + OUTPUT_LOTTO_SIZE_MESSAGE)
    }

    companion object {
        const val OUTPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요."
        const val OUTPUT_LOTTO_SIZE_MESSAGE = "개를 구매했습니다."
    }
}

package lotto.view

class InputView {
    fun inputPurchase(): String {
        println(INPUT_PURCHASE_MESSAGE)
        return readln()
    }

    companion object {
        private const val INPUT_PURCHASE_MESSAGE = "구입금액을 입력해 주세요."
    }
}

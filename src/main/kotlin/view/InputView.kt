package lotto.view

class InputView {
    fun readPurchasePrice(): String {
        println(MESSAGE_INPUT_PURCHASE_PRICE)
        return readln()
    }

    companion object {
        const val MESSAGE_INPUT_PURCHASE_PRICE = "구입금액을 입력해 주세요."
    }
}
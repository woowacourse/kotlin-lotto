package lotto.view

import java.util.Scanner

class InputView {
    private val scanner = Scanner(System.`in`)

    fun readPurchaseAmount(): Int {
        println(READ_PURCHASE_AMOUNT_MESSAGE)
        return scanner.nextInt()
    }

    private companion object {
        const val READ_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요."
    }
}

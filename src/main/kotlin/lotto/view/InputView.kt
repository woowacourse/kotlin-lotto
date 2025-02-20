package lotto.view

import lotto.domain.value.PurchaseAmount
import kotlin.runCatching

class InputView {
    fun readPurchaseAmount():PurchaseAmount{
        println(INPUT_PURCHASE_AMOUNT)
        val inputMessage = readln()
        runCatching { inputMessage.toInt() }.onFailure { throw IllegalArgumentException() }
        return PurchaseAmount(inputMessage.toInt())
    }

    companion object {
        const val INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
    }
}
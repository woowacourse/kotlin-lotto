package lotto.view

import lotto.model.PurchaseAmount

object PurchaseAmountInputView {
    private const val ERROR_PREFIX = "[ERROR]"
    private const val ERROR_EMPTY_INPUT_MESSAGE = "입력값이 없습니다."

    fun readPurchaseAmount(purchaseUnit: Int): PurchaseAmount {
        while (true) {
            println("구입금액을 입력해 주세요.")
            val result =
                runCatching {
                    val input = readlnOrNull() ?: throw IllegalArgumentException(ERROR_EMPTY_INPUT_MESSAGE)
                    PurchaseAmount(input, purchaseUnit)
                }

            result.onSuccess { return it }

            result.onFailure { println("$ERROR_PREFIX ${it.message}") }
        }
    }
}

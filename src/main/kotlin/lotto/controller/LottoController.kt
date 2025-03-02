package lotto.controller

import lotto.domain.valueobject.LottoPaymentMoney
import lotto.view.InputView
import kotlin.runCatching

class LottoController(private val inputView: InputView) {

    fun runLotto() {
        val lottoPaymentMoney: LottoPaymentMoney = retryUntilSuccess { getLottoPaymentMoney() }
    }

    private fun getLottoPaymentMoney(): LottoPaymentMoney = LottoPaymentMoney(inputView.readPayAmount())

    private fun <T> retryUntilSuccess(action: () -> T): T {
        while (true) {
            runCatching { action() }
                .onFailure { e -> println("${e.message}") }
                .getOrNull()
                ?.let { return it }
        }
    }
}
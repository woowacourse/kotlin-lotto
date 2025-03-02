package lotto.controller

import lotto.domain.model.LottoTicket
import lotto.domain.model.ManualLottoTicket
import lotto.domain.valueobject.LottoNumber
import lotto.domain.valueobject.LottoPaymentMoney
import lotto.domain.valueobject.LottoQuantity
import lotto.domain.valueobject.validator.ManualLottoQuantityValidator
import lotto.view.InputView
import lotto.view.OutputView
import kotlin.runCatching

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun runLotto() {
        val lottoPaymentMoney: LottoPaymentMoney = retryUntilSuccess { readLottoPaymentMoney() }
        outputView.showParagraphSeparation()
        val manualLottoQuantity: LottoQuantity = retryUntilSuccess { validateManualLottoQuantity(lottoPaymentMoney) }
        outputView.showParagraphSeparation()
        val manualLottoTickets = createWholeManualLottoTickets(manualLottoQuantity)
    }

    private fun readLottoPaymentMoney(): LottoPaymentMoney = LottoPaymentMoney(inputView.readPayAmount())

    private fun createWholeManualLottoTickets(manualLottoQuantity: LottoQuantity): List<LottoTicket> {
        if (manualLottoQuantity.quantity == 0) return emptyList()
        inputView.showManualLottoNumbersAlert()
        return List(manualLottoQuantity.quantity) { retryUntilSuccess { createSingleManualLottoTicket() } }
    }

    private fun createSingleManualLottoTicket(): LottoTicket =
        ManualLottoTicket(
            inputView.readSingleManualLottoNumbers().map {
                LottoNumber(it)
            },
        )

    private fun validateManualLottoQuantity(lottoPaymentMoney: LottoPaymentMoney): LottoQuantity {
        val manualLottoQuantity = LottoQuantity(inputView.readManualLottoQuantity())
        ManualLottoQuantityValidator().validate(lottoPaymentMoney, manualLottoQuantity)
        return manualLottoQuantity
    }

    private fun <T> retryUntilSuccess(action: () -> T): T {
        while (true) {
            runCatching { action() }
                .onFailure { e -> println("${e.message}") }
                .getOrNull()
                ?.let { return it }
        }
    }
}

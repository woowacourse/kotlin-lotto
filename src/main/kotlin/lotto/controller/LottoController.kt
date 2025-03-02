package lotto.controller

import lotto.domain.model.AutoLottoTicket
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
        val lottoPaymentMoney = getLottoPaymentMoney()
        val (manualQuantity, autoQuantity) = getLottoQuantities(lottoPaymentMoney)
        val boughtTickets = buyLottoTickets(manualQuantity, autoQuantity)
        outputView.showBoughtLottoQuantity(manualQuantity, autoQuantity)
        outputView.showBoughtLottoTickets(boughtTickets)
    }

    private fun getLottoPaymentMoney(): LottoPaymentMoney {
        val money = retryUntilSuccess { readLottoPaymentMoney() }
        outputView.showParagraphSeparation()
        return money
    }

    private fun getLottoQuantities(paymentMoney: LottoPaymentMoney): Pair<LottoQuantity, LottoQuantity> {
        val manualQuantity = retryUntilSuccess { validateManualLottoQuantity(paymentMoney) }
        val autoQuantity = paymentMoney.calculateLeftLotoQuantity(manualQuantity)
        return Pair(manualQuantity, autoQuantity)
    }

    private fun buyLottoTickets(
        manualQuantity: LottoQuantity,
        autoQuantity: LottoQuantity,
    ): List<LottoTicket> {
        val manualTickets = createWholeManualLottoTickets(manualQuantity)
        val autoTickets = createWholeAutoLottoTickets(autoQuantity)
        return manualTickets + autoTickets
    }

    private fun readLottoPaymentMoney(): LottoPaymentMoney = LottoPaymentMoney(inputView.readPayAmount())

    private fun createWholeAutoLottoTickets(autoLottoQuantity: LottoQuantity): List<LottoTicket> {
        if (autoLottoQuantity.quantity == 0) return emptyList()
        return List(autoLottoQuantity.quantity) { AutoLottoTicket() }
    }

    private fun createWholeManualLottoTickets(manualLottoQuantity: LottoQuantity): List<LottoTicket> {
        if (manualLottoQuantity.quantity == 0) return emptyList()
        inputView.showManualLottoNumbersAlert()
        val manualLottoTickets = List(manualLottoQuantity.quantity) { retryUntilSuccess { createSingleManualLottoTicket() } }
        outputView.showParagraphSeparation()
        return manualLottoTickets
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
        outputView.showParagraphSeparation()
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

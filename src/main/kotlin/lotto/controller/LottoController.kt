package lotto.controller

import lotto.domain.model.purchaseInfo.LottoNumber
import lotto.domain.model.purchaseInfo.LottoPaymentMoney
import lotto.domain.model.purchaseInfo.quantity.AutoLottoQuantity
import lotto.domain.model.purchaseInfo.quantity.LottoQuantity
import lotto.domain.model.purchaseInfo.quantity.ManualLottoQuantity
import lotto.domain.model.purchaseInfo.ticket.AutoLottoTicket
import lotto.domain.model.purchaseInfo.ticket.LottoTicket
import lotto.domain.model.purchaseInfo.ticket.ManualLottoTicket
import lotto.domain.model.winning.WinTicket
import lotto.domain.model.winning.WinningStatistics
import lotto.view.InputView
import lotto.view.OutputView
import kotlin.runCatching

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun runLotto() {
        val lottoPaymentMoney = getLottoPaymentMoney()
        val boughtLottoQuantity = lottoPaymentMoney.calculatePossibleBuyLottoQuantity()
        val (manualLottoQuantity, autoLottoQuantity) = getLottoQuantities(boughtLottoQuantity)
        val boughtTickets = buyLottoTickets(manualLottoQuantity, autoLottoQuantity)
        outputView.showBoughtLottoQuantity(manualLottoQuantity, autoLottoQuantity)
        outputView.showBoughtLottoTickets(boughtTickets)
        val winTicket = retryUntilSuccess { getWinTicket() }
        val winningStatistics = WinningStatistics(lottoPaymentMoney, winTicket.calculateWinningStatistics(boughtTickets))
        outputView.showWinningStatics(winningStatistics)
        outputView.showEarningRate(winningStatistics.getEarningRate())
    }

    private fun getLottoPaymentMoney(): LottoPaymentMoney {
        val money = retryUntilSuccess { readLottoPaymentMoney() }
        outputView.showParagraphSeparation()
        return money
    }

    private fun getLottoQuantities(boughtLottoQuantity: LottoQuantity): Pair<ManualLottoQuantity, AutoLottoQuantity> {
        val manualLottoQuantity = retryUntilSuccess { getManualLottoQuantity(boughtLottoQuantity) }
        val autoLottoQuantity = AutoLottoQuantity(boughtLottoQuantity, manualLottoQuantity)
        return Pair(manualLottoQuantity, autoLottoQuantity)
    }

    private fun buyLottoTickets(
        manualLottoQuantity: ManualLottoQuantity,
        autoLottoQuantity: AutoLottoQuantity,
    ): List<LottoTicket> {
        val manualTickets = createWholeManualLottoTickets(manualLottoQuantity)
        val autoTickets = createWholeAutoLottoTickets(autoLottoQuantity)
        return manualTickets + autoTickets
    }

    private fun getWinTicket(): WinTicket {
        val winLottoTicket = retryUntilSuccess { createWinLottoTicket() }
        val bonusNumber = retryUntilSuccess { LottoNumber(inputView.readBonusBallNumber()) }
        outputView.showParagraphSeparation()
        return WinTicket(winLottoTicket, bonusNumber)
    }

    private fun createWinLottoTicket(): LottoTicket =
        ManualLottoTicket(
            inputView.readWinLottoNumbers().map {
                LottoNumber(it)
            },
        )

    private fun readLottoPaymentMoney(): LottoPaymentMoney = LottoPaymentMoney(inputView.readPayAmount())

    private fun createWholeAutoLottoTickets(autoLottoQuantity: AutoLottoQuantity): List<LottoTicket> {
        if (autoLottoQuantity.quantity == 0) return emptyList()
        return List(autoLottoQuantity.quantity) { AutoLottoTicket() }
    }

    private fun createWholeManualLottoTickets(manualLottoQuantity: ManualLottoQuantity): List<LottoTicket> {
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

    private fun getManualLottoQuantity(boughtLottoQuantity: LottoQuantity): ManualLottoQuantity {
        val rawManualLottoQuantity = inputView.readManualLottoQuantity()
        val manualLottoQuantity = ManualLottoQuantity(boughtLottoQuantity, rawManualLottoQuantity)
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

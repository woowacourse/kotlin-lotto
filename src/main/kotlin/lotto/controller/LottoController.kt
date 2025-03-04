package lotto.controller

import lotto.domain.model.lottoticket.AutoLottoTicket
import lotto.domain.model.lottoticket.LottoTicket
import lotto.domain.model.lottoticket.ManualLottoTicket
import lotto.domain.model.winning.Rank
import lotto.domain.model.winning.WinTicketInfo
import lotto.domain.model.winning.WinningStatistics
import lotto.domain.valueobject.LottoNumber
import lotto.domain.valueobject.LottoPaymentMoney
import lotto.domain.valueobject.LottoQuantity
import lotto.domain.valueobject.WinningQuantity
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
        val winTicketInfo = retryUntilSuccess { getWinTicketInfo() }
        val winningStatistics = WinningStatistics(lottoPaymentMoney, getRankCounts(boughtTickets, winTicketInfo))
        outputView.showWinningStatics(winningStatistics)
        outputView.showEarningRate(winningStatistics.getEarningRate())
    }

    private fun getRankCounts(
        boughtTickets: List<LottoTicket>,
        winTicketInfo: WinTicketInfo,
    ): Map<Rank, WinningQuantity> {
        val rawRankCount = boughtTickets.map { it.getRankByWinInfo(winTicketInfo) }.groupingBy { it }.eachCount()
        return rawRankCount.mapValues { (_, value) -> WinningQuantity(value) }
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

    private fun getWinTicketInfo(): WinTicketInfo {
        val winLottoTicket = retryUntilSuccess { createWinLottoTicket() }
        val bonusNumber = retryUntilSuccess { LottoNumber(inputView.readBonusBallNumber()) }
        outputView.showParagraphSeparation()
        return WinTicketInfo(winLottoTicket, bonusNumber)
    }

    private fun createWinLottoTicket(): LottoTicket =
        ManualLottoTicket(
            inputView.readWinLottoNumbers().map {
                LottoNumber(it)
            },
        )

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

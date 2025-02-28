package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.Order
import lotto.domain.WinningLotto
import lotto.generator.LottoManualGenerator
import lotto.generator.LottoRandomGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val lottoMachine = LottoMachine()

    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val lottoTicket = prepareLottoTicket(purchaseAmount)
        val winningLotto = readWinningLotto()
        val lottoResult = createLottoResult(purchaseAmount, lottoTicket, winningLotto)
        showResult(lottoResult)
    }

    private fun getPurchaseAmount(): Int {
        return inputView.getPurchaseAmount().toInt()
    }

    private fun prepareLottoTicket(purchaseAmount: Int): List<Lotto> {
        val order = Order(purchaseAmount)
        val manualLottoCount = getManualLottoCount()
        order.setManualCount(manualLottoCount)
        generateLottoTicket(order)
        val lottoTickets = lottoMachine.getLottoTickets()
        outputView.printPurchasedLottoTickets(manualLottoCount, lottoTickets)
        return lottoTickets
    }

    private fun generateLottoTicket(order: Order) {
        inputView.getManualLottoTickets()
        repeat(order.manualCount) {
            val lotto = inputView.getManualLotto()
            lottoMachine.buyLottoTicket(LottoManualGenerator(lotto))
        }
        val autoCount = order.getAutoCount()
        repeat(autoCount) {
            lottoMachine.buyLottoTicket(LottoRandomGenerator())
        }
    }

    private fun readWinningLotto(): WinningLotto {
        val winningNumber = getInputLotto()
        val bonusNumber = getBonusNumber()
        return WinningLotto(winningNumber, bonusNumber)
    }

    private fun createLottoResult(
        purchaseAmount: Int,
        lottoTicket: List<Lotto>,
        winningLotto: WinningLotto,
    ): LottoResult {
        val lottoResult = LottoResult(winningLotto)
        lottoResult.calculateWinningStats(lottoTicket)
        val prize = lottoResult.calculatePrize()
        lottoResult.calculateProfit(prize, purchaseAmount)
        return lottoResult
    }

    private fun showResult(lottoResult: LottoResult) {
        outputView.printWinningStats(lottoResult.getWinningStats())
        outputView.printProfit(lottoResult.getProfitRate())
    }

    private fun getManualLottoCount(): Int {
        val manualLottoCount = inputView.getManualLottoCount()
        return manualLottoCount.toInt()
    }

    private fun getInputLotto(): Lotto {
        val winningNumber = inputView.getWinningNumber().split(DELIMITERS).map { LottoNumber(it.trim().toInt()) }.toSet()
        return Lotto(winningNumber)
    }

    private fun getBonusNumber(): LottoNumber {
        val bonusNumber = inputView.getBonusNumber()
        return LottoNumber(bonusNumber.toInt())
    }

    companion object {
        const val DELIMITERS = ","
    }
}

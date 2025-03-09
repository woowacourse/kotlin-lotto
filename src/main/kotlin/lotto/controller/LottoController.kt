package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val lottoMachine = LottoMachine()

    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val lottoTickets = prepareLottoTicket()
        val winningLotto = readWinningLotto()
        val lottoResult = createLottoResult(purchaseAmount, lottoTickets, winningLotto)
        showResult(lottoResult)
    }

    private fun getPurchaseAmount(): Int {
        val inputPurchaseAmount = inputView.getPurchaseAmount()
        val purchaseAmount = lottoMachine.validPurchaseAmount(inputPurchaseAmount)
        if (purchaseAmount == null) {
            outputView.printInvalidPurchaseAmountMessage()
            return getPurchaseAmount()
        }
        return purchaseAmount
    }

    private fun prepareLottoTicket(): List<Lotto> {
        val manualLottoCount = getManualLottoCount()
        val manualLottoTickets = getManualLottoTickets(manualLottoCount)
        val lottoTickets = lottoMachine.createTotalLottoTicket(manualLottoTickets)
        outputView.printPurchasedLottoTickets(manualLottoCount, lottoTickets)
        return lottoTickets
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
        val inputManualLottoCount: Int = inputView.getManualLottoCount()
        val manualLottoCount = lottoMachine.validManualLottoCount(inputManualLottoCount)
        if (manualLottoCount == null) {
            outputView.printInvalidLottoCountMessage(lottoMachine.totalLottoCount)
            return getManualLottoCount()
        }
        return manualLottoCount
    }

    private fun getManualLottoTickets(manualLottoCount: Int): List<Lotto> {
        val inputTickets: MutableList<Lotto> = mutableListOf()
        inputView.getManualLottoTickets()
        repeat(manualLottoCount) {
            val manualLottoTicket = processManualLottoTicket()
            inputTickets.add(manualLottoTicket)
        }
        return inputTickets
    }

    private fun processManualLottoTicket(): Lotto {
        val input = inputView.getManualLotto()
        lateinit var manualLotto: Lotto
        runCatching {
            manualLotto = lottoMachine.createManualLottoTicket(input.split(",").map { it.toInt() }.toSet())
        }.onFailure { exception ->
            println(exception)
            manualLotto = processManualLottoTicket()
        }
        return manualLotto
    }

    private fun getInputLotto(): Lotto {
        val winningNumber = inputView.getWinningNumber().split(DELIMITERS).map { LottoNumber.from(it.trim().toInt()) }.toSet()
        return Lotto(winningNumber)
    }

    private fun getBonusNumber(): LottoNumber {
        val bonusNumber = inputView.getBonusNumber().toIntOrNull() ?: return getBonusNumber()
        return LottoNumber.from(bonusNumber)
    }

    companion object {
        const val DELIMITERS = ","
    }
}

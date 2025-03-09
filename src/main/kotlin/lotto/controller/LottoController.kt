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
        val winningLotto = prepareWinningLotto()
        val lottoResult = createLottoResult(purchaseAmount, lottoTickets, winningLotto)
        showResult(lottoResult)
    }

    private fun getPurchaseAmount(): Int {
        val inputPurchaseAmount = inputView.getPurchaseAmount()
        var purchaseAmount: Int = 0
        runCatching {
            purchaseAmount = lottoMachine.validPurchaseAmount(inputPurchaseAmount)
        }.onFailure { exception ->
            outputView.printErrorMessage(exception)
            purchaseAmount = getPurchaseAmount()
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

    private fun prepareWinningLotto(): WinningLotto {
        val winningNumber = getInputLotto()
        val bonusNumber = getBonusNumber()
        lateinit var winningLotto: WinningLotto
        runCatching { winningLotto = WinningLotto.of(winningNumber, bonusNumber) }.onFailure { exception ->
            outputView.printErrorMessage(exception)
            winningLotto = prepareWinningLotto()
        }
        return winningLotto
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
            outputView.printErrorMessage(exception)
            manualLotto = processManualLottoTicket()
        }
        return manualLotto
    }

    private fun getInputLotto(): Lotto {
        lateinit var lottoTicket: Lotto
        runCatching {
            val winningNumber = inputView.getWinningNumber().split(DELIMITERS).map { LottoNumber.from(it.trim().toInt()) }.toSet()
            lottoTicket = Lotto.of(winningNumber)
        }.onFailure { exception ->
            outputView.printErrorMessage(exception)
            lottoTicket = getInputLotto()
        }
        return lottoTicket
    }

    private fun getBonusNumber(): LottoNumber {
        val bonusNumber = inputView.getBonusNumber().toIntOrNull() ?: return getBonusNumber()
        return LottoNumber.from(bonusNumber)
    }

    companion object {
        const val DELIMITERS = ","
    }
}

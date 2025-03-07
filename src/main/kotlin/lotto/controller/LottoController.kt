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
        val lottoTickets = prepareLottoTicket(purchaseAmount)
        val winningLotto = readWinningLotto()
        val lottoResult = createLottoResult(purchaseAmount, lottoTickets, winningLotto)
        showResult(lottoResult)
    }

    private fun getPurchaseAmount(): Int {
        val purchaseAmount = inputView.getPurchaseAmount()
        if (purchaseAmount < 1_000 || purchaseAmount % 1_000 == 0) {
            println("[ERROR] 구입 금액이 올바르지 않습니다. 다시 입력해주세요.")
            return getPurchaseAmount()
        }
        return purchaseAmount
    }

    private fun prepareLottoTicket(purchaseAmount: Int): List<Lotto> {
        val manualLottoCount = getManualLottoCount()
        val autoLottoTickets = lottoMachine.createLottoTicket(purchaseAmount, manualLottoCount)
        val manualLottoTickets = getManualLottoTickets(manualLottoCount)
        val lottoTickets = manualLottoTickets + autoLottoTickets
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
        return inputView.getManualLottoCount()
    }

    private fun getManualLottoTickets(manualLottoCount: Int): List<Lotto> {
        val inputTickets: MutableList<Set<Int>> = mutableListOf()
        inputView.getManualLottoTickets()
        repeat(manualLottoCount) {
            val input = inputView.getManualLotto()
            inputTickets.add(input.split(",").map { it.trim().toInt() }.toSet())
        }
        val manualLotto = lottoMachine.createManualLottoTicket(inputTickets)
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

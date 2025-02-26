package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.WinningLotto
import lotto.generator.LottoManualGenerator
import lotto.generator.LottoRandomGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
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
        val lottoMachine = LottoMachine(purchaseAmount)
        val manualLottoCount = getManualLottoCount()
        val autoLottoCount = LottoMachine(purchaseAmount).getAutoLottoCount(manualLottoCount)
        inputView.getManualLottoTickets()
        repeat(manualLottoCount) {
            val lotto = inputView.getManualLotto()
            lottoMachine.buyLottoTickets(1, LottoManualGenerator(lotto))
        }
        lottoMachine.buyLottoTickets(autoLottoCount, LottoRandomGenerator())
        val lottoTickets = lottoMachine.getLottoTickets()
        outputView.printPurchasedLottoTickets(manualLottoCount, lottoTickets)
        return lottoTickets
    }

    private fun readWinningLotto(): WinningLotto {
        val winningNumber = getLotto()
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

    private fun getLotto(): Lotto {
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

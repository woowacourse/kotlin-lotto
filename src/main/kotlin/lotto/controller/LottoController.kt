package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.LottoPayment
import lotto.domain.LottoResult
import lotto.domain.WinningLotto
import lotto.generator.LottoRandomGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchaseAmount = LottoPayment(getPurchaseAmount())
        val lottoTicket = prepareLottoTicket(purchaseAmount)
        val winningLotto = readWinningLotto()
        val lottoResult = createLottoResult(purchaseAmount, lottoTicket, winningLotto)
        showResult(lottoResult)
    }

    private fun prepareLottoTicket(purchaseAmount: LottoPayment): List<Lotto> {
        val lottoTickets = LottoMachine().buyLottoTickets(purchaseAmount, LottoRandomGenerator())
        outputView.printPurchasedLottoTickets(lottoTickets)
        return lottoTickets
    }

    private fun readWinningLotto(): WinningLotto {
        val winningNumber = getWinningNumber()
        val bonusNumber = getBonusNumber()
        return WinningLotto(winningNumber, bonusNumber)
    }

    private fun createLottoResult(
        purchaseAmount: LottoPayment,
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

    private fun getPurchaseAmount(): Int {
        return inputView.getPurchaseAmount().toInt()
    }

    private fun getWinningNumber(): Lotto {
        val winningNumber = inputView.getWinningNumber().split(DELIMITERS).map { LottoNumber(it.trim().toInt()) }
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

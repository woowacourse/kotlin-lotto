package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.PurchaseAmount
import lotto.model.WinningLotto
import lotto.model.WinningStatus
import lotto.util.LottoNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val totalLottoTickets = generateLotto(purchaseAmount)
        OutputView.printNumberOfTicket(purchaseAmount.numberOfManualTickets, purchaseAmount.getNumberOfAutoTickets())
        OutputView.printLottoTickets(totalLottoTickets)

        val winningStatus = makeResult(totalLottoTickets)
        OutputView.printWinningStatus(winningStatus)
        OutputView.printEarningRate(winningStatus.getEarningRate(purchaseAmount.money))
    }

    private fun getPurchaseAmount(): PurchaseAmount {
        val purchaseAmount = InputView.getPurchaseAmount()
        val numberOfManualTickets = InputView.getNumberOfManualLotto()
        return PurchaseAmount(purchaseAmount, numberOfManualTickets)
    }

    private fun generateLotto(purchaseAmount: PurchaseAmount): List<Lotto> {
        val manualLottoTickets = generateManualLotto(purchaseAmount.numberOfManualTickets)
        val autoLottoTickets = generateAutoLotto(purchaseAmount.getNumberOfAutoTickets())
        return manualLottoTickets.plus(autoLottoTickets)
    }

    private fun generateManualLotto(numberOfManualTickets: Int): List<Lotto> {
        val manualLottoNumbers = InputView.getManualLottoNumbers(numberOfManualTickets)
        return LottoMachine.issueManualTickets(manualLottoNumbers)
    }

    private fun generateAutoLotto(numberOfAutoTickets: Int): List<Lotto> {
        return LottoMachine.issueAutoTickets(numberOfAutoTickets, LottoNumbersGenerator)
    }

    private fun makeResult(lottoTickets: List<Lotto>): WinningStatus {
        val lotteryResult =
            WinningLotto(Lotto(InputView.getWinningNumbers()), InputView.getBonusNumber())
        return WinningStatus(lotteryResult.generateWinningStatus(lottoTickets))
    }
}

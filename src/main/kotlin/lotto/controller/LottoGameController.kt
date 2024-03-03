package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.PurchaseAmount
import lotto.model.WinningLotto
import lotto.model.WinningStatus
import lotto.util.LottoNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val lottoCount = getLottoCount(purchaseAmount)
        val totalLottoTickets = generateLottoTickets(lottoCount)

        OutputView.printNumberOfTicket(lottoCount.numberOfManualLotto, lottoCount.getNumberOfAutoTickets())
        OutputView.printLottoTickets(totalLottoTickets)

        val winningStatus = makeResult(totalLottoTickets)
        OutputView.printWinningStatus(winningStatus)
        OutputView.printEarningRate(winningStatus.getEarningRate(purchaseAmount))
    }

    private fun getPurchaseAmount(): PurchaseAmount {
        val purchaseAmount = InputView.getPurchaseAmount()
        return PurchaseAmount(purchaseAmount)
    }

    private fun getLottoCount(purchaseAmount: PurchaseAmount): LottoCount {
        val totalLottoCount = purchaseAmount.getTotalNumberOfLotto()
        val manualLottoCount = InputView.getNumberOfManualLotto()
        return LottoCount(totalLottoCount, manualLottoCount)
    }

    private fun generateLottoTickets(lottoCount: LottoCount): List<Lotto> {
        val manualNumbers = InputView.getManualLottoNumbers(lottoCount.numberOfManualLotto)
        return LottoMachine.issueLotto(lottoCount, manualNumbers, LottoNumbersGenerator)
    }

    private fun getWinningLotto(): Lotto {
        val numbers = InputView.getWinningNumbers()
        val winningNumbers = numbers.map { LottoNumber.valueOf(it) }.toSet()
        return Lotto(winningNumbers)
    }

    private fun getBonusNumber(): LottoNumber = LottoNumber.valueOf(InputView.getBonusNumber())

    private fun makeResult(lottoTickets: List<Lotto>): WinningStatus {
        val winningLotto = WinningLotto(getWinningLotto(), getBonusNumber())
        return winningLotto.generateWinningStatus(lottoTickets)
    }
}

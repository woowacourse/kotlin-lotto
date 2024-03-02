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
        return runCatching { PurchaseAmount(purchaseAmount) }.getOrNull() ?: getPurchaseAmount()
    }

    private fun getLottoCount(purchaseAmount: PurchaseAmount): LottoCount {
        val totalLottoCount = purchaseAmount.getTotalNumberOfLotto()
        val manualLottoCount = InputView.getNumberOfManualLotto()
        return LottoCount.from(totalLottoCount, manualLottoCount) ?: getLottoCount(purchaseAmount)
    }

    private fun generateLottoTickets(lottoCount: LottoCount): List<Lotto> {
        val manualNumbers = InputView.getManualLottoNumbers(lottoCount.numberOfManualLotto)
        return runCatching {
            LottoMachine.issueLotto(lottoCount, manualNumbers, LottoNumbersGenerator)
        }.getOrNull() ?: generateLottoTickets(lottoCount)
    }

    private fun getWinningLotto(): Lotto {
        val winningNumbers = InputView.getWinningNumbers().mapNotNull { LottoNumber.valueOf(it) }.toSet()
        val winningLotto = runCatching { Lotto(winningNumbers) }.getOrNull()
        return winningLotto ?: getWinningLotto()
    }

    private fun getBonusNumber(): LottoNumber = LottoNumber.valueOf(InputView.getBonusNumber()) ?: getBonusNumber()

    private fun makeResult(lottoTickets: List<Lotto>): WinningStatus {
        val winningLotto = WinningLotto(getWinningLotto(), getBonusNumber())
        return winningLotto.generateWinningStatus(lottoTickets)
    }
}

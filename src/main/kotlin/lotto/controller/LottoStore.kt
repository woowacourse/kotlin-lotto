package lotto.controller

import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket
import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoMachine
import lotto.domain.service.LottoResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoStore(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val (manualCount, autoCount) = getLottoCount()
        val lottoTickets = generateLottoTickets(manualCount, autoCount)
        outputView.printLotto(lottoTickets)

        val winningLotto = getWinningLotto()

        val result = calculateResult(lottoTickets, winningLotto)
        outputView.printResult(result.ranks)
        outputView.printProfit(result.calculateProfit())
    }

    private fun getLottoCount(): Pair<Int, Int> {
        val lottoMachine = LottoMachine()
        val amount = inputView.inputPurchaseAmount()
        val totalCount = lottoMachine.calculateTotalCount(amount)
        val manualCount = inputView.inputManualCount()
        val autoCount = lottoMachine.calculateAutoCount(totalCount, manualCount)
        return Pair(manualCount, autoCount)
    }

    private fun generateLottoTickets(
        manualCount: Int,
        autoCount: Int,
    ): List<LottoTicket> {
        outputView.printManualNumbersGuide()
        val manualLottoTickets = generateManualLottoTicket(manualCount)
        val autoLottoTickets = generateAutoLottoTicket(autoCount)
        outputView.printPurchaseCount(manualCount, autoCount)
        return manualLottoTickets + autoLottoTickets
    }

    private fun generateManualLottoTicket(count: Int): List<LottoTicket> {
        val manualLottoTickets =
            List(count) {
                LottoTicket(inputView.inputManualNumbers().map { LottoNumber(it) })
            }
        return manualLottoTickets
    }

    private fun generateAutoLottoTicket(count: Int): List<LottoTicket> {
        val autoLottoTickets = LottoMachine().generateAutoTicket(count)
        return autoLottoTickets
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = inputView.inputWinningNumbers()
        val bonusNumber = LottoNumber(inputView.inputBonusNumber())
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        return winningLotto
    }

    private fun calculateResult(
        lottoTickets: List<LottoTicket>,
        winningLotto: WinningLotto,
    ): LottoResult = winningLotto.getResult(lottoTickets)
}

package lotto.controller

import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoMachine
import lotto.domain.service.LottoResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoStore(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    private val lottoMachine = LottoMachine()

    fun run() {
        val (manualCount, autoCount) = getLottoCount()
        outputView.printManualNumbersGuide()
        val manualLottoTickets = generateManualLottoTicket(manualCount)
        val autoLottoTickets = generateAutoLottoTicket(autoCount)
        outputView.printPurchaseCount(manualCount, autoCount)
        outputView.printLotto(manualLottoTickets)
        outputView.printLotto(autoLottoTickets)
        val winningLotto = getWinningLotto()
        val result = calculateResult(manualLottoTickets, autoLottoTickets, winningLotto)
        val formattedWinningStatus = formattingWinningStatus(result)
        outputView.printResult(formattedWinningStatus)
        outputView.printProfit(result.calculateProfit())
    }

    private fun getLottoCount(): Pair<Int, Int> {
        val amount = inputView.inputPurchaseAmount()
        val totalCount = lottoMachine.calculateTotalCount(amount)
        val manualCount = inputView.inputManualCount()
        val autoCount = lottoMachine.calculateAutoCount(totalCount, manualCount)
        return Pair(manualCount, autoCount)
    }

    private fun generateManualLottoTicket(count: Int): List<LottoTicket> =
        List(count) {
            LottoTicket(inputView.inputManualNumbers().map { LottoNumber(it) })
        }

    private fun generateAutoLottoTicket(count: Int): List<LottoTicket> = LottoMachine().generateAutoTicket(count)

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = inputView.inputWinningNumbers()
        val bonusNumber = LottoNumber(inputView.inputBonusNumber())
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        return winningLotto
    }

    private fun calculateResult(
        manualLottoTickets: List<LottoTicket>,
        autoLottoTickets: List<LottoTicket>,
        winningLotto: WinningLotto,
    ): LottoResult {
        val result = winningLotto.getResult(manualLottoTickets, autoLottoTickets)
        return result
    }

    private fun formattingWinningStatus(result: LottoResult): Map<Rank, Int> {
        val resultMap = mutableMapOf<Rank, Int>()
        Rank.entries.filter { it != Rank.MISS }.map { rank ->
            resultMap[rank] = result.getWinningStatus().getOrDefault(rank, 0)
        }
        return resultMap
    }
}

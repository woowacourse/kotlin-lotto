package lotto.controller

import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoMachine
import lotto.domain.service.LottoResult
import lotto.domain.service.LottoScanner
import lotto.view.InputView
import lotto.view.OutputView

class LottoStore(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    private val lottoMachine = LottoMachine()
    private lateinit var lottoTickets: MutableList<LottoTicket>

    fun run() {
        val autoCount = inputLottoInfo()
        val autoLottoTickets = generateLottoTicket(autoCount)
        lottoTickets.addAll(autoLottoTickets)
        outputView.printLotto(lottoTickets)
        val winningLotto = inputWinningLotto()
        val result = calculateResult(lottoTickets, winningLotto)
        val formattedWinningStatus = formattingWinningStatus(result)
        outputView.printResult(formattedWinningStatus)
        outputView.printProfit(result.calculateProfit())
    }

    private fun inputLottoInfo(): Int {
        val amount = inputView.inputPurchaseAmount()
        val totalCount = lottoMachine.calculateTotalCount(amount)
        val manualCount = inputView.inputManualCount()
        val manualTickets = generateManualLottoTicket(manualCount)
        lottoTickets = manualTickets.toMutableList()
        val autoCount = lottoMachine.calculateAutoCount(totalCount, manualCount)
        outputView.printPurchaseCount(manualCount, autoCount)
        return autoCount
    }

    private fun generateManualLottoTicket(count: Int): List<LottoTicket> =
        List(count) {
            LottoTicket(inputView.inputManualNumbers().map { LottoNumber(it) }.toSet())
        }

    private fun generateLottoTicket(count: Int): List<LottoTicket> = LottoMachine().purchase(count)

    private fun inputWinningLotto(): WinningLotto {
        val winningNumbers = inputView.inputWinningNumbers().map { LottoNumber(it) }.toSet()
        val bonusNumber = LottoNumber(inputView.inputBonusNumber())
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        return winningLotto
    }

    private fun calculateResult(
        lottoTickets: List<LottoTicket>,
        winningLotto: WinningLotto,
    ): LottoResult {
        val result = LottoScanner(lottoTickets, winningLotto).getResult()
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

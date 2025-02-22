package lotto

import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoResult
import lotto.model.LottoScanner
import lotto.model.LottoTicket
import lotto.model.Rank
import lotto.view.InputView
import lotto.view.OutputView

class LottoStore(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    private fun calculatePurchaseCount(amount: Int) = amount / Constants.LOTTO_AMOUNT

    private fun generateLottoTicket(count: Int): List<LottoTicket> = LottoMachine().purchase(count)

    private fun calculateResult(
        lottoTickets: List<LottoTicket>,
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): LottoResult {
        val winning = winningNumbers.map { LottoNumber(it) }.toSet()
        val result = LottoScanner(lottoTickets, winning, LottoNumber(bonusNumber)).getResult()
        return result
    }

    private fun formattingWinningStatus(result: LottoResult): Map<Rank, Int> {
        val resultMap = mutableMapOf<Rank, Int>()
        Rank.entries.filter { it != Rank.MISS }.map { rank ->
            resultMap[rank] = result.getWinningStatus().getOrDefault(rank, 0)
        }
        return resultMap
    }

    fun run() {
        val amount = inputView.inputPurchaseAmount()
        val count = calculatePurchaseCount(amount)
        outputView.printPurchaseCount(count)
        val lottoTickets = generateLottoTicket(count)
        outputView.printLotto(lottoTickets)
        val winningNumbers = inputView.inputWinningNumbers()
        val bonusNumber = inputView.inputBonusNumber()
        val result = calculateResult(lottoTickets, winningNumbers, bonusNumber)
        val formattedWinningStatus = formattingWinningStatus(result)
        outputView.printResult(formattedWinningStatus)
        outputView.printProfit(result.calculateProfit())
    }
}

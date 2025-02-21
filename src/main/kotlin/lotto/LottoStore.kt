package lotto

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoResult
import lotto.model.LottoScanner
import lotto.model.Rank
import lotto.view.InputView
import lotto.view.OutputView

class LottoStore(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    private fun calculatePurchaseCount(amount: Int) = amount / Constants.LOTTO_AMOUNT

    private fun generateLottoTicket(count: Int): List<Lotto> {
        val lottoTickets = LottoMachine(count).lottoTickets
        lottoTickets.map {
            outputView.printLotto(it)
        }
        return lottoTickets
    }

    private fun calculateResult(
        lottoTicket: List<Lotto>,
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): LottoResult {
        val winning = Lotto(winningNumbers.map { LottoNumber(it) }, LottoNumber(bonusNumber))
        val result = LottoScanner(winning).getResult(lottoTicket)
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
        val lottoTicket = generateLottoTicket(count)
        val winningNumbers = inputView.inputWinningNumbers()
        val bonusNumber = inputView.inputBonusNumber()
        val result = calculateResult(lottoTicket, winningNumbers, bonusNumber)
        val formattedWinningStatus = formattingWinningStatus(result)
        outputView.printResult(formattedWinningStatus)
        outputView.printProfit(result.calculateProfit())
    }
}

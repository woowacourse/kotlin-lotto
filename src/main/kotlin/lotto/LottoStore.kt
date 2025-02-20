package lotto

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoResult
import lotto.model.LottoScanner
import lotto.view.InputView
import lotto.view.OutputView

class LottoStore(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun calculatePurchaseCount(amount: Int) = amount / 1000

    fun formattingWinningStatus(result: LottoResult): Map<Rank, Int> {
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
        val lottoTicket = LottoMachine(count)
        lottoTicket.lottos.map {
            outputView.printLotto(it)
        }
        val winningNumbers = inputView.inputWinningNumbers()
        val bonusNumber = inputView.inputBonusNumber()
        val winning = Lotto(winningNumbers.map { LottoNumber(it) }, LottoNumber(bonusNumber))
        val result = LottoScanner(winning).getResult(lottoTicket.lottos)
        val formattedWinningStatus = formattingWinningStatus(result)
        outputView.printResult(formattedWinningStatus)
        outputView.printProfit(result.calculateProfit())
    }
}

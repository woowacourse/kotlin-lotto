package lotto

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoScanner
import lotto.view.InputView
import lotto.view.OutputView

class LottoStore(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun calculatePurchaseCount(amount: Int) = amount / 1000

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
        println(result.getWinningStatus())
        outputView.printResult(result)
        outputView.printProfit(result.calculateProfit())
    }
}

package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoFactory
import lotto.domain.LottoResult
import lotto.domain.Purchase
import lotto.domain.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val price = inputView.inputPurchasePrice()
        val amount = Purchase(price).calculateAmountOfLottos()
        outputView.printLottoAmount(amount)

        val lottos: List<Lotto> = LottoFactory().generateLottos(amount)
        outputView.printLottos(lottos.map { it.lottoNums })

        val winningNumbers: List<Int> = inputView.inputWinningNumber()
        val bonusNumber: String = inputView.inputBonusNumber()
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val profitRate: Double = LottoResult(lottos, winningLotto).calculateProfitRate()

        outputView.printResult(LottoResult(lottos, winningLotto))
        outputView.printProfit(profitRate)
    }
}

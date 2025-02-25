package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMatchCalculator
import lotto.model.LottoNumber
import lotto.model.LottoStatisticResult
import lotto.model.LottoTicketCounter
import lotto.model.RandomLottoGenerator
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun play() {
        val purchase = inputView.inputPurchase()
        val count = LottoTicketCounter(purchase).ticketCount
        outputView.printLottoCount(count)

        val lottoBundle = purchaseLottos(count)
        outputView.printLottoBundle(lottoBundle)

        val winningLotto = WinningLotto(getWinningLotto(), LottoNumber(getBonusNumber()))
        val lottoMatchResult = LottoMatchCalculator().getWinningCounts(lottoBundle, winningLotto)

        val lottoStatisticResult = LottoStatisticResult().calculateProfit(purchase.toInt(), lottoMatchResult)

        outputView.printResult(lottoMatchResult, lottoStatisticResult)
    }

    private fun getWinningLotto(): Lotto {
        val input = inputView.inputWinningNumbers()
        val lottoNumber = input.map { LottoNumber(it) }
        return Lotto(lottoNumber)
    }

    private fun getBonusNumber(): Int = inputView.inputBonusNumber()

    private fun purchaseLottos(count: Int): List<Lotto> {
        return RandomLottoGenerator().generate(count)
    }
}

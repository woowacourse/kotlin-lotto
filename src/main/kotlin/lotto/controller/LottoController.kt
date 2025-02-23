package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMatchCalculator
import lotto.model.LottoStatisticResult
import lotto.model.LottoStore
import lotto.model.LottoTicketCounter
import lotto.model.RandomLottoGenerator
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()
    private val lottoStore = LottoStore()

    fun play() {
        val purchase = inputView.inputPurchase()
        val count = LottoTicketCounter(purchase).count()
        outputView.printLottoCount(count)

        val lottoBundle = purchaseLottos(count)
        outputView.printLottoBundle(lottoBundle)

        val winningLotto = WinningLotto(getWinningLotto(), getBonusNumber())
        val lottoMatchResult = LottoMatchCalculator().getWinningCounts(lottoBundle, winningLotto)

        val lottoStatisticResult = LottoStatisticResult().calculateProfit(purchase.toInt(), lottoMatchResult)

        outputView.printResult(lottoMatchResult, lottoStatisticResult)
    }

    private fun getWinningLotto(): Lotto = Lotto(inputView.inputWinningNumbers())

    private fun getBonusNumber(): Int = inputView.inputBonusNumber()

    private fun purchaseLottos(count: Int): List<Lotto> {
        return lottoStore.getTickets(count, RandomLottoGenerator())
    }
}

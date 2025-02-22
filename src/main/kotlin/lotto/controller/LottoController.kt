package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoResult
import lotto.model.LottoStore
import lotto.model.LottoTicketCounter
import lotto.model.RandomLottoGenerator
import lotto.model.Rank
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
        val winningCounts = calculateResult(lottoBundle, winningLotto)
        val profit = calculateProfit(purchase, winningCounts)
        outputView.printResult(winningCounts, profit)
    }

    private fun getWinningLotto(): Lotto = Lotto(inputView.inputWinningNumbers())

    private fun getBonusNumber(): Int = inputView.inputBonusNumber()

    private fun purchaseLottos(count: Int): List<Lotto> {
        return lottoStore.getTickets(count, RandomLottoGenerator())
    }

    private fun calculateResult(
        lottoBundle: List<Lotto>,
        winningLotto: WinningLotto,
    ): Map<Rank, Int> {
        return LottoResult().getWinningCounts(lottoBundle, winningLotto)
    }

    private fun calculateProfit(
        input: String,
        winningCounts: Map<Rank, Int>,
    ): String {
        return LottoResult().calculateProfit(input, winningCounts)
    }
}

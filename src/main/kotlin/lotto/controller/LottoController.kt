package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.WinningNumbers
import lotto.domain.service.LottoStore
import lotto.domain.service.LottosMachine
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lottosMachine: LottosMachine = LottosMachine(),
    private val lottoStore: LottoStore = LottoStore(),
) {
    fun run() {
        val lottos = purchaseLotto()
        val winningLotto = Lotto(inputView.readLottoNumbers())
        val bonusNumber = LottoNumber(inputView.readBonusNumber())
        val winningNumbers = WinningNumbers(winningLotto, bonusNumber)

        printWinningResults(lottos, winningNumbers)
    }

    private fun purchaseLotto(): List<Lotto> {
        val purchasePrice = inputView.readPurchaseAmount()
        val lottoCount = lottoStore.getLottoCount(purchasePrice)
        val lottos = lottosMachine.generate(lottoCount)
        outputView.printPurchaseLottoCount(lottos.size)
        lottos.forEach { lotto -> outputView.printPurchaseLottoNumbers(lotto.numbers) }
        return lottos
    }

    private fun printWinningResults(
        lottos: List<Lotto>,
        winningNumbers: WinningNumbers,
    ) {
        val lottoRanks = winningNumbers.calculateLottoRanks(lottos)
        outputView.printWinningResults(lottoRanks)
        outputView.printTotalReturns(lottoRanks.calculateTotalReturn())
    }
}

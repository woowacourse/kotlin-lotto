package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.PurchaseAmount
import lotto.domain.model.WinningNumbers
import lotto.domain.service.LottosMachine
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lottosMachine: LottosMachine = LottosMachine(),
) {
    fun run() {
        val lottos = purchaseLotto()
        val winningLotto = Lotto(inputView.readLottoNumbers())
        val bonusNumber = LottoNumber(inputView.readBonusNumber())
        val winningNumbers = WinningNumbers(winningLotto, bonusNumber)

        printWinningResults(lottos, winningNumbers)
    }

    private fun purchaseLotto(): List<Lotto> {
        val purchaseAmount = PurchaseAmount(inputView.readPurchaseAmount())
        val manualCount = purchaseAmount.purchaseLotto(inputView.readPassivityLottoCount())
        val lottos = lottosMachine.generateRandomLottos(manualCount)
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

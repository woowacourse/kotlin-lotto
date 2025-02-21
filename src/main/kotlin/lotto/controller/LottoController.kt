package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.WinningNumbers
import lotto.domain.service.LottoRankCalculator
import lotto.domain.service.LottosGenerator
import lotto.domain.service.TotalReturnCalculator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lottosGenerator: LottosGenerator = LottosGenerator(),
    private val totalReturnCalculator: TotalReturnCalculator = TotalReturnCalculator(),
    private val lottoRankCalculator: LottoRankCalculator = LottoRankCalculator(),
) {
    fun run() {
        val lottos = purchaseLotto()
        val winningLotto = Lotto(inputView.readWinningNumbers().map { LottoNumber(it) }.toSet())
        val bonusNumber = LottoNumber(inputView.readBonusNumber())
        val winningNumbers = WinningNumbers(winningLotto, bonusNumber)

        printWinningResults(lottos, winningNumbers)
    }

    private fun purchaseLotto(): List<Lotto> {
        val purchasePrice = inputView.readPurchaseAmount()
        val lottos = lottosGenerator.generate(purchasePrice)
        outputView.printPurchaseLottoCount(lottos.size)
        lottos.forEach { lotto -> outputView.printPurchaseLottoNumbers(lotto.numbers.toList()) }
        return lottos
    }

    private fun printWinningResults(
        lottos: List<Lotto>,
        winningNumbers: WinningNumbers,
    ) {
        val lottoRanks = lottos.map { lotto -> lottoRankCalculator.calculate(lotto, winningNumbers) }
        outputView.printWinningResults(lottoRanks)
        outputView.printTotalReturns(totalReturnCalculator.calculate(lottoRanks))
    }
}

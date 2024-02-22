package lotto.controller

import lotto.model.DrawResult
import lotto.model.Lotto
import lotto.model.LottoAnalyzer
import lotto.model.LottoBundle
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun start() {
        val lottoBundle = buyLottoBundle()
        val drawResult = lottoDraw()
        matchResult(lottoBundle, drawResult)
    }

    private fun buyLottoBundle(): LottoBundle {
        val price = InputView.readPrice()
        val lottoMachine = LottoMachine(price)
        val lottoBundle = lottoMachine.createLottoBundle()
        OutputView.printLottoBundle(lottoBundle)
        return lottoBundle
    }

    private fun lottoDraw(): DrawResult {
        val winningNumbers = InputView.readWinningNumbers()
        val lottoNumbers = winningNumbers.map { LottoNumber(it) }
        val winningLotto = Lotto(lottoNumbers)
        val bonusNumber = InputView.readBonusNumber()

        return DrawResult(winningLotto, LottoNumber(bonusNumber))
    }

    private fun matchResult(
        lottoBundle: LottoBundle,
        drawResult: DrawResult,
    ) {
        val lottoAnalyzer = LottoAnalyzer(lottoBundle, drawResult)
        val lottoResult = lottoAnalyzer.calculateResult()
        OutputView.printResult(lottoResult)
    }
}

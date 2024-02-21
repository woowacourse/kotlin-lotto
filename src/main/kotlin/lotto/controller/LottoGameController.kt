package lotto.controller

import lotto.model.DrawResult
import lotto.model.Lotto
import lotto.model.LottoAnalyzer
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun start() {
        val lottos = buyLottos()
        val drawResult = lottoDraw()
        matchResult(lottos, drawResult)
    }

    private fun buyLottos(): List<Lotto> {
        val price = InputView.readPrice()
        val lottoMachine = LottoMachine(price)
        val lottos = lottoMachine.createLottos()
        OutputView.printLottos(lottos)
        return lottos
    }

    private fun lottoDraw(): DrawResult {
        val winningNumber = InputView.readWinningNumbers()
        val winningLotto = Lotto((winningNumber.split(",").map { LottoNumber(it.trim()) }))
        val bonusNumber = InputView.readBonusNumber()

        return DrawResult(winningLotto, LottoNumber(bonusNumber))
    }

    private fun matchResult(
        lottos: List<Lotto>,
        drawResult: DrawResult,
    ) {
        val lottoAnalyzer = LottoAnalyzer(lottos, drawResult)
        val lottoResult = lottoAnalyzer.calculateResult()
        OutputView.printResult(lottoResult)
    }
}

package lotto.controller

import lotto.model.DrawResult
import lotto.model.Lotto
import lotto.model.LottoAnalyzer
import lotto.model.LottoBundle
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoResponse
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun start() {
        getLottoBundleAndDrawResult().onSuccess { response ->
            matchResult(response.lottoBundle, response.drawResult)
        }.onFailure { e ->
            OutputView.printError(e)
        }
    }

    private fun getLottoBundleAndDrawResult(): Result<LottoResponse> =
        runCatching {
            val lottoBundle = buyLottoBundle()
            val drawResult = lottoDraw()
            LottoResponse(lottoBundle, drawResult)
        }

    private fun buyLottoBundle(): LottoBundle {
        val price = InputView.readPrice()
        val lottoBundle = LottoMachine.createLottoBundle(price)
        OutputView.printLottoBundle(lottoBundle)
        return lottoBundle
    }

    private fun lottoDraw(): DrawResult {
        val winningNumbers = InputView.readWinningNumbers()
        val lottoNumbers = winningNumbers.map { LottoNumber.from(it) }.toSet()
        val winningLotto = Lotto(lottoNumbers)
        val bonusNumber = InputView.readBonusNumber()

        return DrawResult(winningLotto, LottoNumber.from(bonusNumber))
    }

    private fun matchResult(
        lottoBundle: LottoBundle,
        drawResult: DrawResult,
    ) {
        val lottoResult = LottoAnalyzer.calculateResult(lottoBundle, drawResult)
        OutputView.printResult(lottoResult)
    }
}

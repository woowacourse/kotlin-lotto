package lotto.controller

import lotto.model.DrawResult
import lotto.model.GameState
import lotto.model.Lotto
import lotto.model.LottoAnalyzer
import lotto.model.LottoBundle
import lotto.model.LottoMachine
import lotto.model.LottoManualPurchase
import lotto.model.LottoNumber
import lotto.model.MatchResultResponse
import lotto.model.Price
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun start() {
        var gameState: GameState = GameState.Play

        while (gameState == GameState.Play) {
            getMatchResult().onSuccess { response ->
                calculateMatchResult(response)
                gameState = GameState.End
            }.onFailure { e ->
                OutputView.printError(e)
            }
        }
    }

    private fun getMatchResult(): Result<MatchResultResponse> =
        runCatching {
            val lottoBundle = buyLottoBundle()
            val drawResult = lottoDraw()
            MatchResultResponse(lottoBundle, drawResult)
        }

    private fun buyLottoBundle(): LottoBundle {
        val money = InputView.readMoney()
        val price = Price.from(money)
        val lottoMachine = LottoMachine(price)
        return createLottoBundle(lottoMachine)
    }

    private fun getLottoManualPurchase(): LottoManualPurchase {
        val lottoManualPurchaseCount = InputView.readLottoManualPurchaseCount()
        return LottoManualPurchase.from(lottoManualPurchaseCount)
    }

    private fun createLottoBundle(lottoMachine: LottoMachine): LottoBundle {
        val lottoManualPurchase = getLottoManualPurchase()
        val randomLottoCount = lottoMachine.getRandomLottoCount(lottoManualPurchase)
        val lottoManualPurchaseNumbers = InputView.readLottoManualPurchaseNumbers(lottoManualPurchase)
        val lottoBundle = lottoMachine.createLottoBundle(lottoManualPurchaseNumbers, lottoManualPurchase)

        OutputView.printLottoCount(lottoManualPurchase, randomLottoCount)
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

    private fun calculateMatchResult(matchResultResponse: MatchResultResponse) {
        val lottoResult = LottoAnalyzer.calculateResult(matchResultResponse)
        OutputView.printResult(lottoResult)
    }
}

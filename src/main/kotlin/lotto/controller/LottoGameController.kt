package lotto.controller

import lotto.model.DrawResult
import lotto.model.GameState
import lotto.model.Lotto
import lotto.model.LottoBundle
import lotto.model.LottoMachine
import lotto.model.LottoManualPurchaseCount
import lotto.model.LottoNumber
import lotto.model.ManualPurchaseLottos
import lotto.model.MatchResult
import lotto.model.Price
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController(
    private var gameState: GameState = GameState.Play,
    private var price: Price = Price(),
) {
    fun start() {
        while (gameState == GameState.Play) {
            getMatchResult().onSuccess { matchResult ->
                val lottoResult = matchResult.calculateResult()
                OutputView.printResult(lottoResult.toString(), lottoResult.getProfitRate())
                price = price.minusPrice()
                gameState = GameState.End
            }.onFailure { e ->
                OutputView.printError(e)
            }
        }
    }

    private fun getMatchResult(): Result<MatchResult> =
        runCatching {
            val lottoBundle = buyLottoBundle()
            val drawResult = lottoDraw()
            MatchResult(lottoBundle, drawResult)
        }

    private fun buyLottoBundle(): LottoBundle {
        val money = InputView.readMoney()
        price = price.plusPrice(money)
        return createLottoBundle()
    }

    private fun getLottoManualPurchaseCount(): LottoManualPurchaseCount {
        val count = InputView.readLottoManualPurchaseCount()
        return LottoManualPurchaseCount.from(count)
    }

    private fun createLottoBundle(): LottoBundle {
        val lottoManualPurchaseCount = getLottoManualPurchaseCount()
        val randomLottoCount = price.getRandomLottoCount(lottoManualPurchaseCount.count)
        val lottoManualPurchaseNumbers = InputView.readLottoManualPurchaseNumbers(lottoManualPurchaseCount.count)
        val lottoBundle =
            LottoMachine.createLottoBundle(ManualPurchaseLottos(lottoManualPurchaseNumbers), randomLottoCount)

        OutputView.printLottoCount(lottoManualPurchaseCount.count, randomLottoCount)
        OutputView.printLottoBundle(lottoBundle.toString())
        return lottoBundle
    }

    private fun lottoDraw(): DrawResult {
        val winningNumbers = InputView.readWinningNumbers()
        val lottoNumbers = winningNumbers.map { LottoNumber.from(it) }.toSet()
        val winningLotto = Lotto(lottoNumbers)
        val bonusNumber = InputView.readBonusNumber()

        return DrawResult(winningLotto, LottoNumber.from(bonusNumber))
    }
}

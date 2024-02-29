package lotto.controller

import lotto.exception.Exceptions.InvalidPurchaseAmountException
import lotto.exception.Exceptions.PurchaseAmountNotNaturalNumberException
import lotto.model.DrawResult
import lotto.model.GameState
import lotto.model.Kiosk
import lotto.model.Lotto
import lotto.model.LottoBundle
import lotto.model.LottoManualPurchaseCount
import lotto.model.LottoNumber
import lotto.model.ManualPurchaseLottos
import lotto.model.MatchResult
import lotto.model.Money
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController(
    private var gameState: GameState = GameState.Play,
    private val kiosk: Kiosk = Kiosk(),
) {
    fun start() {
        while (true) {
            when (gameState) {
                GameState.Play -> play()
                GameState.End -> break
            }
        }
    }

    private fun play() {
        getMatchResult().onSuccess { matchResult ->
            val lottoResult = matchResult.calculateResult()
            OutputView.printResult(lottoResult.toString(), lottoResult.getProfitRate())
            kiosk.useDepositForLottoTickets()
            gameState = GameState.End
        }.onFailure { e ->
            handleFailedMatch(e)
        }
    }

    private fun handleFailedMatch(e: Throwable) {
        when (e) {
            is PurchaseAmountNotNaturalNumberException -> Unit
            is InvalidPurchaseAmountException -> Unit
            else -> kiosk.refundLastPurchase()
        }
        OutputView.printError(e)
    }

    private fun getMatchResult(): Result<MatchResult> =
        runCatching {
            val lottoBundle = buyLottoBundle()
            val drawResult = lottoDraw()
            MatchResult(lottoBundle, drawResult)
        }

    private fun buyLottoBundle(): LottoBundle {
        val strMoney = InputView.readMoney()
        val money = Money.from(strMoney)
        kiosk.addDeposit(money)
        return createLottoBundle()
    }

    private fun createLottoBundle(): LottoBundle {
        val lottoManualPurchaseCount = getLottoManualPurchaseCount()
        val randomLottoCount = kiosk.getRandomLottoCount(lottoManualPurchaseCount)
        val lottoManualPurchaseNumbers = InputView.readLottoManualPurchaseNumbers(lottoManualPurchaseCount.count)
        val lottoBundle = kiosk.createLottoBundle(ManualPurchaseLottos(lottoManualPurchaseNumbers), randomLottoCount)

        OutputView.printLottoCount(lottoManualPurchaseCount.count, randomLottoCount)
        OutputView.printLottoBundle(lottoBundle.toString())
        return lottoBundle
    }

    private fun getLottoManualPurchaseCount(): LottoManualPurchaseCount {
        val count = InputView.readLottoManualPurchaseCount()
        return LottoManualPurchaseCount.from(count)
    }

    private fun lottoDraw(): DrawResult {
        val winningNumbers = InputView.readWinningNumbers()
        val lottoNumbers = winningNumbers.map { LottoNumber.from(it) }.toSet()
        val winningLotto = Lotto(lottoNumbers)
        val bonusNumber = InputView.readBonusNumber()

        return DrawResult(winningLotto, LottoNumber.from(bonusNumber))
    }
}

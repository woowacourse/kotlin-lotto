package controller

import model.Buyer
import model.Lotto
import model.LottoNumber
import model.Lottos
import model.ManualLottoPurchaseCount
import model.NumberGenerator
import model.Rank
import model.WinningLotto
import model.WinningStatistic
import model.WinningStatistics
import util.LottoConstants
import view.InputView
import view.OutputView

class LottoController {
    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        val money = Buyer.from(purchaseAmount)
        val purchaseSizeOfManualLotto = InputView.inputPurchaseSizeOfManualLotto()
        val manualLottoCount = ManualLottoPurchaseCount.from(purchaseSizeOfManualLotto, money.purchaseAmount)
        val userLotto = publishLottos(manualLottoCount, money)
        val winningLotto = drawWinningLotto()
        val winningStatistics = makeWinningStatics(userLotto, winningLotto)
        displayWinningStatistics(money.numberOfLotto, winningStatistics)
    }

    private fun generateLottoNumbers(): List<LottoNumber> {
        val numberGenerator = NumberGenerator()
        val randomNumbers = numberGenerator.makeRandomNumbers()

        return numberGenerator.drawSixNumbers(randomNumbers).map { LottoNumber(it) }
    }

    private fun publishLottos(
        numberOfManualLotto: ManualLottoPurchaseCount,
        money: Buyer,
    ): Lottos {
        val manual = InputView.inputManualLottos(numberOfManualLotto.count)
        val manualLottos = Lottos(manual.map { generateManualLotto(it) })
        val autoLottos = Lottos(List(money.numberOfLotto - manualLottos.publishedLottos.size) { generateAutoLotto() })

        displayPurchaseResult(manualLottos, autoLottos)

        return Lottos(manualLottos.publishedLottos + autoLottos.publishedLottos)
    }

    private fun generateManualLotto(manual: List<String>): Lotto {
        return Lotto(manual.map { LottoNumber.from(it) })
    }

    private fun generateAutoLotto(): Lotto {
        val autoLotto = Lotto(generateLottoNumbers())
        return autoLotto
    }

    private fun displayPurchaseResult(
        manualLotto: Lottos,
        autoLotto: Lottos,
    ) {
        OutputView.outputNumberOfLotto(manualLotto.publishedLottos.size, autoLotto.publishedLottos.size)
        OutputView.outputLottos(manualLotto)
        OutputView.outputLottos(autoLotto)
    }

    private fun drawWinningLotto(): WinningLotto {
        val inputWinningNumbers = InputView.inputWinningNumbers()
        val winningNumbers = inputWinningNumbers.map { LottoNumber.from(it) }
        val bonusNumber = LottoNumber.from(InputView.inputBonusNumber())
        return WinningLotto(winningNumbers, bonusNumber)
    }

    private fun makeWinningStatics(
        lottos: Lottos,
        winningLotto: WinningLotto,
    ): WinningStatistics {
        val results =
            MutableList(LottoConstants.SIZE) {
                WinningStatistic(Pair(Rank.getRankByOrdinal(it), 0))
            }
        repeat(lottos.publishedLottos.size) { index ->
            val rank = judgeRank(lottos.publishedLottos[index], winningLotto)
            val currentCount = results[rank.ordinal].result.second
            results[rank.ordinal] = WinningStatistic(Pair(rank, currentCount + 1))
        }

        return WinningStatistics(results)
    }

    private fun judgeRank(
        lotto: Lotto,
        winningLotto: WinningLotto,
    ): Rank {
        val countOfMatch = winningLotto.calculateCountOfMatch(lotto)
        val bonusMatched = winningLotto.checkBonusNumberMatched(lotto)
        return Rank.getRank(countOfMatch, bonusMatched)
    }

    private fun displayWinningStatistics(
        purchaseAmount: Int,
        winningStatistics: WinningStatistics,
    ) {
        OutputView.outputWinningStatistics(winningStatistics)
        displayRateOfReturn(purchaseAmount, winningStatistics)
    }

    private fun displayRateOfReturn(
        purchaseAmount: Int,
        winningStatistics: WinningStatistics,
    ) {
        val rateOfReturn = winningStatistics.calculateRateOfReturn(purchaseAmount)
        OutputView.outputRateOfReturn(rateOfReturn)
    }
}

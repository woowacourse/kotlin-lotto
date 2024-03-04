package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Lottos
import lotto.model.ManualLottoPurchaseCount
import lotto.model.Money
import lotto.model.NumberGenerator
import lotto.model.Rank
import lotto.model.WinningLotto
import lotto.model.WinningStatistic
import lotto.model.WinningStatistics
import lotto.util.LottoConstants
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    fun run() {
        playLotto()
    }

    private fun playLotto() =
        runCatching {
            val purchaseAmount = InputView.inputPurchaseAmount()
            val money = Money.from(purchaseAmount)
            val purchaseSizeOfManualLotto = InputView.inputPurchaseSizeOfManualLotto()
            val manualLottoCount = ManualLottoPurchaseCount.from(purchaseSizeOfManualLotto, money.numberOfLotto)
            val userLotto = publishLottos(manualLottoCount, money)
            val winningLotto = drawWinningLotto()

            val winningStatistics = makeWinningStatics(userLotto, winningLotto)
            displayWinningStatistics(money.numberOfLotto, winningStatistics)
        }

    private fun publishLottos(
        numberOfManualLotto: ManualLottoPurchaseCount,
        money: Money,
    ): Lottos {
        val manual = InputView.inputManualLottos(numberOfManualLotto.count)
        val manualLottos = Lottos(manual.map { generateManualLotto(it) })
        val autoLottoCount = money.calculateAutoLottoCount(manualLottos.publishedLottos.size)
        val autoLottos = Lottos(List(autoLottoCount) { generateAutoLotto() })

        displayPurchaseResult(manualLottos, autoLottos)

        return manualLottos + autoLottos
    }

    private fun generateManualLotto(manual: List<String>): Lotto {
        return Lotto(manual.map { LottoNumber(it.toIntOrNull() ?: 0) }.toSet())
    }

    private fun generateAutoLotto(): Lotto {
        return Lotto(generateLottoNumbers())
    }

    private fun generateLottoNumbers(): Set<LottoNumber> {
        return NumberGenerator().generateLottoNumbers().map { LottoNumber(it) }.toSet()
    }

    private fun displayPurchaseResult(
        manualLotto: Lottos,
        autoLotto: Lottos,
    ) {
        OutputView.outputShowLottos(manualLotto, autoLotto)
    }

    private fun drawWinningLotto(): WinningLotto {
        val inputWinningNumbers = InputView.inputWinningNumbers()
        val winningNumbers = inputWinningNumbers.map { LottoNumber(it.toIntOrNull() ?: 0) }
        val bonusNumber = LottoNumber(InputView.inputBonusNumber().toIntOrNull() ?: 0)
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

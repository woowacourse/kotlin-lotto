package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoPrize
import lotto.model.Lottos
import lotto.model.WinningLotto
import lotto.model.WinningStatistic
import lotto.model.WinningStatistics
import lotto.util.Constant
import lotto.view.InputView
import lotto.view.OutputView

object LottoController {
    private const val DEFAULT_COUNT = 0

    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        val numberOfLotto = calculateNumberOfLotto(purchaseAmount)
        val handpickedLottos = publishHandpickedLottos(numberOfLotto)
        val lottos = Lottos()
        lottos.publishLottos(numberOfLotto, handpickedLottos)
        displayPurchaseResult(lottos, handpickedLottos.size)
        val winningLotto = generateWinningLotto()
        val winningStatistics = analyzeWinningStatics(lottos, winningLotto)
        displayWinningStatistics(winningStatistics, lottos.getLottos().size)
    }

    private fun calculateNumberOfLotto(purchaseAmount: Int): Int {
        return purchaseAmount / Constant.PURCHASE_AMOUNT_UNIT
    }

    private fun publishHandpickedLottos(numberOfLotto: Int): List<Lotto> {
        val numberOfHandpickedNumber = InputView.inputNumberOfHandpickedLotto(numberOfLotto)
        var handpickedLottosNumber = listOf<List<Int>>()
        if (numberOfHandpickedNumber != DEFAULT_COUNT) {
            handpickedLottosNumber = InputView.inputHandpickedLottosNumber(numberOfHandpickedNumber)
        }
        return handpickedLottosNumber.map { handpickedLottoNumber ->
            Lotto(handpickedLottoNumber.map { LottoNumber(it) })
        }
    }

    private fun displayPurchaseResult(
        lottos: Lottos,
        numberOfHandpickedLotto: Int,
    ) {
        val numberOfAutomaticLotto = lottos.getLottos().size - numberOfHandpickedLotto
        OutputView.outputNumberOfLotto(numberOfHandpickedLotto, numberOfAutomaticLotto)
        OutputView.outputLottos(lottos)
    }

    private fun generateWinningLotto(): WinningLotto {
        val lotto = Lotto(InputView.inputWinningNumbers().map { LottoNumber(it) })
        val bonusNumber = LottoNumber(InputView.inputBonusNumber(lotto.getNumbers().map { it.getNumber() }))
        return WinningLotto(lotto, bonusNumber)
    }

    private fun analyzeWinningStatics(
        lottos: Lottos,
        winningLotto: WinningLotto,
    ): WinningStatistics {
        val results = mutableMapOf<LottoPrize, Int>()
        for (prize in LottoPrize.entries) {
            results[prize] = DEFAULT_COUNT
        }
        lottos.getLottos().forEach { lotto ->
            val lottoPrize = judgeLottoPrize(lotto, winningLotto)
            results[lottoPrize] = results.getValue(lottoPrize) + 1
        }

        return WinningStatistics(
            results.entries.map { (prize, count) ->
                WinningStatistic(Pair(prize, count))
            },
        )
    }

    private fun judgeLottoPrize(
        lotto: Lotto,
        winningLotto: WinningLotto,
    ): LottoPrize {
        val countOfMatch = winningLotto.calculateCountOfMatch(lotto)
        val bonusMatched = winningLotto.checkBonusNumberMatched(lotto)
        return LottoPrize.getLottoPrize(countOfMatch, bonusMatched)
    }

    private fun displayWinningStatistics(
        winningStatistics: WinningStatistics,
        numberOfLotto: Int,
    ) {
        OutputView.outputWinningStatistics(winningStatistics)
        val rateOfReturn = winningStatistics.calculateRateOfReturn(numberOfLotto)
        OutputView.outputRateOfReturn(rateOfReturn)
    }
}

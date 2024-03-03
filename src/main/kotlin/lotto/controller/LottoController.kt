package lotto.controller

import lotto.model.AutomaticLotto
import lotto.model.HandpickedLotto
import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoPrize
import lotto.model.Lottos
import lotto.model.WinningLotto
import lotto.model.WinningStatistic
import lotto.model.WinningStatistics
import lotto.view.InputView
import lotto.view.OutputView

object LottoController {
    private const val DEFAULT_COUNT = 0

    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        val lottos = buyLottos(purchaseAmount)
        val winningLotto = generateWinningLotto()
        analyzeWinningStatics(lottos, winningLotto)
    }

    private fun buyLottos(purchaseAmount: Int): Lottos {
        val lottos = Lottos()
        val numberOfLotto = lottos.calculateNumberOfLotto(purchaseAmount)
        val handpickedLottos = publishHandpickedLottos(numberOfLotto)
        val automaticLottos = publishAutomaticLottos(numberOfLotto - handpickedLottos.size)
        lottos.publishLottos(handpickedLottos, automaticLottos)
        displayPurchaseResult(lottos, handpickedLottos.size)

        return lottos
    }

    private fun publishHandpickedLottos(numberOfLotto: Int): List<Lotto> {
        val numberOfHandpickedNumber = InputView.inputNumberOfHandpickedLotto(numberOfLotto)
        var handpickedLottosNumber = listOf<List<Int>>()
        if (numberOfHandpickedNumber != DEFAULT_COUNT) {
            handpickedLottosNumber = InputView.inputHandpickedLottosNumber(numberOfHandpickedNumber)
        }
        return handpickedLottosNumber.map { handpickedNumbers ->
            val handpickedLotto = HandpickedLotto(handpickedNumbers.map { LottoNumber(it) })
            handpickedLotto.generateLotto()
        }
    }

    private fun publishAutomaticLottos(numberOfLotto: Int): List<Lotto> {
        val automaticLotto = AutomaticLotto()
        return List(numberOfLotto) { automaticLotto.generateLotto() }
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
    ) {
        val results = mutableMapOf<LottoPrize, Int>()
        for (prize in LottoPrize.entries) {
            results[prize] = DEFAULT_COUNT
        }
        lottos.getLottos().forEach { lotto ->
            val lottoPrize = judgeLottoPrize(lotto, winningLotto)
            results[lottoPrize] = results.getValue(lottoPrize) + 1
        }
        val winningStatistics =
            WinningStatistics(
                results.entries.map { (lottoPrize, count) ->
                    WinningStatistic(lottoPrize, count)
                },
            )
        displayWinningStatistics(winningStatistics, lottos.getLottos().size)
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

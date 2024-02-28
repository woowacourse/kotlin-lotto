package controller

import model.Lotto
import model.LottoNumber
import model.LottoPrize
import model.Lottos
import model.WinningLotto
import model.WinningStatistic
import model.WinningStatistics
import util.Constant
import view.InputView
import view.OutputView

object LottoController {
    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        val numberOfLotto = calculateNumberOfLotto(purchaseAmount)
        val handpickedLottos = publishHandpickedLottos(numberOfLotto)
        val lottos = Lottos()
        lottos.publishLottos(numberOfLotto, handpickedLottos)
        displayPurchaseResult(lottos, handpickedLottos.size)
        val winningLotto = drawWinningLotto()
        val winningStatistics = analyzeWinningStatics(lottos, winningLotto)
        displayWinningStatistics(winningStatistics, lottos.getLottos().size)
    }

    private fun calculateNumberOfLotto(purchaseAmount: Int): Int {
        return purchaseAmount / Constant.PURCHASE_AMOUNT_UNIT
    }

    private fun publishHandpickedLottos(numberOfLotto: Int): List<Lotto> {
        val numberOfHandpickedNumber = InputView.inputNumberOfHandpickedLotto(numberOfLotto)
        val handpickedLottosNumber = mutableListOf<List<Int>>()
        if (numberOfHandpickedNumber != 0) {
            handpickedLottosNumber.addAll(InputView.inputHandpickedLottosNumber(numberOfHandpickedNumber))
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

    private fun drawWinningLotto(): WinningLotto {
        val lotto = Lotto(InputView.inputWinningNumbers().map { LottoNumber(it) })
        val bonusNumber = LottoNumber(InputView.inputBonusNumber(lotto.getNumbers().map { it.getNumber() }))
        return WinningLotto(lotto, bonusNumber)
    }

    private fun analyzeWinningStatics(
        lottos: Lottos,
        winningLotto: WinningLotto,
    ): WinningStatistics {
        val results =
            MutableList(6) {
                WinningStatistic(Pair(LottoPrize.getLottoPrizeByOrdinal(it), 0))
            }
        repeat(lottos.getLottos().size) { index ->
            val lottoPrize = judgeLottoPrize(lottos.getLottos()[index], winningLotto)
            val currentCount = results[lottoPrize.ordinal].getWinningStatistic().second
            results[lottoPrize.ordinal] = WinningStatistic(Pair(lottoPrize, currentCount + 1))
        }
        return WinningStatistics(results)
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

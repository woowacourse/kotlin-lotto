package controller

import model.Lotto
import model.LottoNumber
import model.LottoPrize
import model.Lottos
import model.WinningLotto
import model.WinningStatistic
import model.WinningStatistics
import view.InputView
import view.OutputView

class LottoController {
    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        val handpickedLottos = publishHandpickedLottos()
        val lottos = Lottos(purchaseAmount, handpickedLottos)
        displayPurchaseResult(lottos)

        val winningLotto = drawWinningLotto()
        val winningStatistics = analyzeWinningStatics(lottos, winningLotto)
        displayWinningStatistics(winningStatistics, purchaseAmount)
    }

    private fun publishHandpickedLottos(): List<Lotto> {
        val numberOfHandpickedNumber = InputView.inputNumberOfHandpickedLotto()
        val handpickedLottosNumber = mutableListOf<List<Int>>()
        if (numberOfHandpickedNumber != 0) {
            handpickedLottosNumber.addAll(InputView.inputHandpickedLottosNumber(numberOfHandpickedNumber))
        }

        return handpickedLottosNumber.map { handpickedLottoNumber ->
            Lotto(handpickedLottoNumber.map { LottoNumber(it) })
        }
    }

    private fun displayPurchaseResult(lottos: Lottos) {
        val numberOfHandpickedLotto = lottos.getHandPickedLottos().size
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
        purchaseAmount: Int,
    ) {
        OutputView.outputWinningStatistics(winningStatistics)

        val rateOfReturn = winningStatistics.calculateRateOfReturn(purchaseAmount)
        OutputView.outputRateOfReturn(rateOfReturn)
    }
}

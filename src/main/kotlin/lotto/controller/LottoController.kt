package lotto.controller

import lotto.model.AutoLottoMachine
import lotto.model.Lotto
import lotto.model.LottoMatcher
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseInfo
import lotto.model.ManualLottoMachine
import lotto.model.PrizeCalculator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val lottoPurchaseInfo = getLottoPurchaseInfo()
        val publishedLotto = publishLotto(lottoPurchaseInfo)
        val winningLotto = getWinningLotto()
        val bonusNumber = getBonusNumber()
        val lottoMatcher = LottoMatcher(winningLotto, bonusNumber)
        showEarningRate(lottoPurchaseInfo, lottoMatcher, publishedLotto)
    }

    private fun getLottoPurchaseInfo(): LottoPurchaseInfo {
        outputView.printAmountMessage()
        val amountInput = inputView.getSingleNumber()
        outputView.printManualLottoCountMessage()
        val countInput = inputView.getSingleNumber()
        return LottoPurchaseInfo(amountInput, countInput)
    }

    private fun getLottoNumbersByManual(lottoPurchaseInfo: LottoPurchaseInfo): List<Lotto> {
        if (lottoPurchaseInfo.manualLottoCount <= 0) {
            return emptyList()
        }
        outputView.printManualLottoNumberMessage()
        val manualNumbers =
            List(lottoPurchaseInfo.manualLottoCount) {
                inputView.getMultipleNumber()
            }
        return ManualLottoMachine(manualNumbers).publishLottoTickets(lottoPurchaseInfo)
    }

    private fun publishLotto(lottoPurchaseInfo: LottoPurchaseInfo): List<Lotto> {
        val manualLottoList = getLottoNumbersByManual(lottoPurchaseInfo)
        val autoLottoList = AutoLottoMachine().publishLottoTickets(lottoPurchaseInfo)
        val publishedLotto = manualLottoList + autoLottoList
        outputView.printPublishedLotto(lottoPurchaseInfo, publishedLotto.map { it.toString() })
        return publishedLotto
    }

    private fun getWinningLotto(): Lotto {
        outputView.printWinningNumberMessage()
        val winningInput = inputView.getMultipleNumber()
        return Lotto.from(winningInput)
    }

    private fun getBonusNumber(): LottoNumber {
        outputView.printBonusMessage()
        val bonusInput = inputView.getSingleNumber()
        return LottoNumber(bonusInput)
    }

    private fun showEarningRate(
        lottoPurchaseInfo: LottoPurchaseInfo,
        lottoMatcher: LottoMatcher,
        publishedLotto: List<Lotto>,
    ) {
        val result = lottoMatcher.matchLotto(publishedLotto)
        val earningRate = PrizeCalculator().calculateEarningRate(lottoPurchaseInfo.amount, result)
        outputView.printPrize(result, earningRate)
    }
}

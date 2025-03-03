package lotto.controller

import lotto.domain.AutoLottoMachine
import lotto.domain.LottoMatcher
import lotto.domain.ManualLottoMachine
import lotto.domain.PrizeCalculator
import lotto.domain.model.Amount
import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoPurchaseInfo
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
        showEarningRate(lottoPurchaseInfo.amount, lottoMatcher, publishedLotto)
    }

    private fun getLottoPurchaseInfo(): LottoPurchaseInfo {
        outputView.printAmountMessage()
        val amount = Amount(inputView.getSingleNumber())
        outputView.printManualLottoCountMessage()
        val countInput = inputView.getSingleNumber()
        return LottoPurchaseInfo(amount, countInput)
    }

    private fun getLottoNumbersByManual(lottoPurchaseInfo: LottoPurchaseInfo): List<Lotto> {
        if (lottoPurchaseInfo.manualLottoCount <= 0) {
            return ManualLottoMachine().publishLottoTickets(lottoPurchaseInfo)
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
        amount: Amount,
        lottoMatcher: LottoMatcher,
        publishedLotto: List<Lotto>,
    ) {
        val result = lottoMatcher.matchLotto(publishedLotto)
        val earningRate = PrizeCalculator().calculateEarningRate(amount.value, result)
        outputView.printPrize(result, earningRate)
    }
}

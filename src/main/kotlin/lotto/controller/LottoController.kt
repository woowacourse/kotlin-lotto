package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoMatcher
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseAmount
import lotto.model.PrizeCalculator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val lottoPurchaseAmount = getLottoPurchaseAmount()
        val lottoMachine = LottoMachine()
        val publishedLotto = publishLotto(lottoMachine, lottoPurchaseAmount)
        val winningLotto = getWinningLotto()
        val bonusNumber = getBonusNumber()
        val lottoMatcher = LottoMatcher(winningLotto, bonusNumber)
        showEarningRate(lottoPurchaseAmount, lottoMatcher, publishedLotto)
    }

    private fun getLottoPurchaseAmount(): LottoPurchaseAmount {
        outputView.printAmountMessage()
        val amountInput = inputView.getSingleNumber()
        return LottoPurchaseAmount(amountInput)
    }

    private fun publishLotto(
        lottoMachine: LottoMachine,
        lottoPurchaseAmount: LottoPurchaseAmount,
    ): List<Lotto> {
        val publishedLotto = lottoMachine.publishLottoTickets(lottoPurchaseAmount.getLottoQuantity())
        outputView.printPublishedLotto(lottoPurchaseAmount.getLottoQuantity(), publishedLotto)
        return publishedLotto
    }

    private fun getWinningLotto(): Lotto {
        outputView.printWinningNumberMessage()
        val winningInput = inputView.getMultipleNumber()
        return Lotto(winningInput.map(::LottoNumber))
    }

    private fun getBonusNumber(): LottoNumber {
        outputView.printBonusMessage()
        val bonusInput = inputView.getSingleNumber()
        return LottoNumber(bonusInput)
    }

    private fun showEarningRate(
        lottoPurchaseAmount: LottoPurchaseAmount,
        lottoMatcher: LottoMatcher,
        publishedLotto: List<Lotto>,
    ) {
        val result = lottoMatcher.matchLotto(publishedLotto)
        val prizeCalculator = PrizeCalculator()
        val earningRate = prizeCalculator.calculateEarningRate(lottoPurchaseAmount.amount, result)
        outputView.printPrize(result, earningRate)
    }
}

package lotto.controller

import lotto.model.Amount
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoMatcher
import lotto.model.LottoNumber
import lotto.model.PrizeCalculator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val amount = getAmount()
        val lottoMachine = LottoMachine(amount)
        val publishedLotto = publishLotto(lottoMachine, amount)
        val winningLotto = getWinningLotto()
        val bonusNumber = getBonusNumber()
        val lottoMatcher = LottoMatcher(winningLotto, bonusNumber)
        showEarningRate(amount, lottoMatcher, publishedLotto)
    }

    private fun getAmount(): Amount {
        outputView.printAmountMessage()
        val amountInput = inputView.getSingleNumber()
        return Amount(amountInput)
    }

    private fun publishLotto(
        lottoMachine: LottoMachine,
        amount: Amount,
    ): List<Lotto> {
        val publishedLotto = lottoMachine.publishLottoTickets()
        outputView.printPublishedLotto(amount.getLottoQuantity(), publishedLotto)
        return publishedLotto
    }

    private fun getWinningLotto(): Lotto {
        outputView.printWinningNumberMessage()
        val winningInput = inputView.getMultipleNumber()
        return Lotto(winningInput.map { number -> LottoNumber(number) })
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
        val prizeCalculator = PrizeCalculator()
        val earningRate = prizeCalculator.calculateEarningRate(amount.money, result)
        outputView.printPrize(result, earningRate)
    }
}

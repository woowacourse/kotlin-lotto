package lotto.controller

import lotto.model.Amount
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoMatcher
import lotto.model.LottoNumber
import lotto.model.LottoNumbers
import lotto.model.PrizeCalculator
import lotto.view.InputMessage.BONUS_NUMBER_INPUT_MESSAGE
import lotto.view.InputMessage.MONEY_INPUT_MESSAGE
import lotto.view.InputMessage.WINNING_LOTTO_INPUT_MESSAGE
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val amount = getAmount()
        val lottoMachine = LottoMachine(amount)
        val publishedLotto = publishLotto(lottoMachine)
        val winningLotto = getWinningLotto()
        val bonusNumber = getBonusNumber()
        val lottoMatcher = LottoMatcher(winningLotto, bonusNumber)
        showEarningRate(amount, lottoMatcher, publishedLotto)
    }

    private fun getAmount(): Amount {
        val amountInput = inputView.getSingleNumber(MONEY_INPUT_MESSAGE)
        val amount = Amount(amountInput)
        return amount
    }

    private fun publishLotto(lottoMachine: LottoMachine): List<Lotto> {
        val publishedLotto = lottoMachine.publishLottoTickets(Amount(LOTTO_PRIZE))
        outputView.printPublishedLotto(publishedLotto)
        return publishedLotto
    }

    private fun getWinningLotto(): Lotto {
        val winningInput = inputView.getMultipleNumber(WINNING_LOTTO_INPUT_MESSAGE)
        return Lotto(LottoNumbers(winningInput.map { number -> LottoNumber(number) }), Amount(LOTTO_PRIZE))
    }

    private fun getBonusNumber(): LottoNumber {
        val bonusInput = inputView.getSingleNumber(BONUS_NUMBER_INPUT_MESSAGE)
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

    companion object {
        const val LOTTO_PRIZE = 1000
    }
}

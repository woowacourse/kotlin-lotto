package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoMatcher
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseAmount
import lotto.model.PrizeCalculator
import lotto.model.Rank
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val lottoPurchaseAmount = getLottoPurchaseAmount()
        val publishedLotto = publishLotto(lottoPurchaseAmount)
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

    private fun publishLotto(lottoPurchaseAmount: LottoPurchaseAmount): List<Lotto> {
        val publishedLotto = LottoMachine().publishLottoTickets(lottoPurchaseAmount.getLottoQuantity())
        val formattedLottoNumbers =
            publishedLotto.map { lotto ->
                "[${lotto.numbers.joinToString(",") { it.value.toString() }}]"
            }
        outputView.printPublishedLotto(lottoPurchaseAmount.getLottoQuantity(), formattedLottoNumbers)
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
        lottoPurchaseAmount: LottoPurchaseAmount,
        lottoMatcher: LottoMatcher,
        publishedLotto: List<Lotto>,
    ) {
        val result = lottoMatcher.matchLotto(publishedLotto)
        val earningRate = PrizeCalculator().calculateEarningRate(lottoPurchaseAmount.amount, result)
        val formattedResult =
            Rank.entries.associateWith { rank ->
                result.getOrDefault(rank, 0)
            }
        outputView.printPrize(formattedResult, earningRate)
    }
}

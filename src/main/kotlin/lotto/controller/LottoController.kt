package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoFactory
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.PurchaseAmount
import lotto.domain.WinningLotto
import lotto.service.LottoGenerator
import lotto.util.retryWhenException
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val price = getPurchasePrice()
        val amount = PurchaseAmount(price).calculateAmountOfLottos()
        outputView.printLottoAmount(amount)

        val lottoFactory = LottoFactory(LottoGenerator())
        val lottos = lottoFactory.generateLottos(amount)

        outputView.printLottos(lottos)

        val winningNumbers: Lotto = getWinningNumbers()
        val bonusNumber: LottoNumber = getBonusNumber()
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)

        val profitRate: Double = LottoResult(lottos, winningLotto).calculateProfitRate()

        outputView.printResult(LottoResult(lottos, winningLotto))
        outputView.printProfit(profitRate)
    }

    private fun getPurchasePrice(): Int =
        retryWhenException(
            action = {
                val input = inputView.inputPurchasePrice()
                input.toInt()
            },
            onError = {
                outputView.printErrorMessage(it)
            },
        )

    private fun getWinningNumbers(): Lotto =
        retryWhenException(
            action = {
                val input = inputView.inputWinningNumber()
                Lotto(input.map { number -> LottoNumber(number.toInt()) })
            },
            onError = {
                outputView.printErrorMessage(it)
            },
        )

    private fun getBonusNumber(): LottoNumber =
        retryWhenException(
            action = {
                val input = InputView.inputBonusNumber()
                LottoNumber(input.toInt())
            },
            onError = {
                outputView.printErrorMessage(it)
            },
        )
}

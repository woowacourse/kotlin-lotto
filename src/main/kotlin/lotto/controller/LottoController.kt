package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoFactory
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.Purchase
import lotto.domain.WinningLotto
import lotto.validator.InputValidator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val price = inputView.inputPurchasePrice()
        val amount = Purchase(price).calculateAmountOfLottos()
        outputView.printLottoAmount(amount)

        val lottos: List<Lotto> = LottoFactory().generateLottos(amount)
        outputView.printLottos(lottos)

        val winningNumbers: Lotto = getWinningNumber()
        val bonusNumber: LottoNumber = getBonusNumber()
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)

        val profitRate: Double = LottoResult(lottos, winningLotto).calculateProfitRate()

        outputView.printResult(LottoResult(lottos, winningLotto))
        outputView.printProfit(profitRate)
    }

    private fun getWinningNumber(): Lotto {
        val winningLotto: String = inputView.inputWinningNumber()
        return Lotto(
            winningLotto.split(",").map {
                InputValidator().validateNumber(it)
                LottoNumber(it.toInt())
            },
        )
    }

    private fun getBonusNumber(): LottoNumber {
        val bonusNumber: String = inputView.inputBonusNumber()
        InputValidator().validateNumber(bonusNumber)
        return LottoNumber(bonusNumber.toInt())
    }
}

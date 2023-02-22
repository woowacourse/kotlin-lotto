package controller

import domain.*
import view.InputView
import view.OutputView

class LottoController {
    private val inputView by lazy { InputView() }
    private val outputView by lazy { OutputView() }

    fun run() {
        val amount = askAmount()
        val numberOfLottosToBuyManually = askNumberOfLottosToBuyManually()
        val lottosToBuyManually = askLottosToBuyManually(numberOfLottosToBuyManually)
        val lottos =
            lottosToBuyManually + getLotto(amount - Money(LottoStore.LOTTO_PRICE) * numberOfLottosToBuyManually)
        outputView.outputLottos(numberOfLottosToBuyManually, lottos)
        val winningLotto = WinningLotto(askWinningNumbers(), askBonusNumber())
        val result = LottoResult.of(lottos, winningLotto)
        outputView.outputResult(result)
    }

    private fun askAmount(): Money {
        outputView.outputGetAmount()

        var amount = 0
        while (true) {
            kotlin.runCatching {
                amount = inputView.inputAmount()
                Money(amount)
            }
                .onSuccess { return Money(amount) }
                .onFailure { outputView.printErrorMessage(it) }
        }
    }

    private fun askNumberOfLottosToBuyManually(): Int {
        outputView.outputGetNumberOfLottosToBuyManually()
        return inputView.inputNumberOfLottosToBuyManually()
    }

    private fun askLottosToBuyManually(count: Int): List<Lotto> {
        outputView.outputGetLottosToBuyManually()
        return inputView.inputLottosToBuyManually(count)
    }

    private fun getLotto(amount: Money): List<Lotto> {
        return LottoStore.sell(amount)
    }

    private fun askWinningNumbers(): Lotto {
        outputView.outputGetWinningNumbers()
        return Lotto.create(inputView.inputWinningNumbers())
    }

    private fun askBonusNumber(): LottoNumber {
        outputView.outputGetBonusNumber()
        return LottoNumber.valueOf(inputView.inputBonusNumber())
    }
}

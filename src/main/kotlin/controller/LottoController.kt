package controller

import domain.Lotto
import domain.LottoNumber
import domain.LottoStore
import domain.WinningLotto
import view.InputView
import view.OutputView

class LottoController {
    private val inputView by lazy { InputView() }
    private val outputView by lazy { OutputView() }

    fun run() {
        val amount = askAmount()
        printPurchaseResult(amount)
        val winningLotto = WinningLotto(askWinningNumbers(), askBonusNumber())
    }

    private fun askAmount(): Int {
        outputView.outputGetAmount()
        return inputView.inputAmount()
    }

    private fun printPurchaseResult(amount: Int) {
        val store = LottoStore()
        val lottos = store.buyLotto(amount)
        outputView.outputLottos(lottos)
    }

    private fun askWinningNumbers(): Lotto {
        outputView.outputGetWinningNumbers()
        return Lotto.create(inputView.inputWinningNumbers())
    }

    private fun askBonusNumber(): LottoNumber {
        outputView.outputGetBonusNumber()
        return LottoNumber(inputView.inputBonusNumber())
    }
}
